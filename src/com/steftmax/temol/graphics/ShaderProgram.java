package com.steftmax.temol.graphics;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

import com.steftmax.temol.resource.Disposable;

/**
 * A basic wrapper around a ShaderProgram.
 * @author pieter3457
 *
 */
public class ShaderProgram implements Disposable {

	private int programID = 0, vertexID = 0, fragmentID = 0;

	public ShaderProgram(String vertex, String fragment) {

		programID = glCreateProgram();

		// initialize vertex
		if (vertex != null) {
			vertexID = createShader(vertex, GL_VERTEX_SHADER);
			glAttachShader(programID, vertexID);
		}

		// initialize fragment
		if (fragment != null) {
			fragmentID = createShader(fragment, GL_FRAGMENT_SHADER);
			glAttachShader(programID, fragmentID);
		}
		// Link the program
		glLinkProgram(programID);
		// Validate the program
		glValidateProgram(programID);
	}

	private int createShader(String shaderSource, int shaderType) {

		int shaderID = glCreateShader(shaderType);

		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);

		// Error checking
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Shader " + shaderSource + " didn't compile!");
		}

		return shaderID;
	}

	public void bind() {

		glUseProgram(programID);
	}

	public void unbind() {
		glUseProgram(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.resource.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		if (programID != 0)
			glDeleteProgram(programID);

		if (vertexID != 0)
			glDeleteShader(vertexID);

		if (fragmentID != 0)
			glDeleteShader(fragmentID);
	}
}
