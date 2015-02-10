package com.steftmax.temol.graphics;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.graphics.sprite.Texture;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * a basic wrapper around a framebufferobject
 * @author pieter3457
 *
 */
public class FrameBuffer {
	private int id;
	private Texture texture;
	
	public FrameBuffer(int width, int height) {
		this(new Texture(width, height));
	}
	
	public FrameBuffer(Texture texture) {
		this.texture = texture;
		texture.bind();
		
		id = glGenFramebuffersEXT();
		glBindFramebufferEXT(GL_FRAMEBUFFER, id);
		glFramebufferTexture2DEXT(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture.getID(), 0);
		glBindFramebufferEXT(GL_FRAMEBUFFER, 0);
	}
	
	public void begin() {
		glViewport(0, 0, texture.width, texture.height);
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, id);
	}
	
	public void end() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
	}
}
