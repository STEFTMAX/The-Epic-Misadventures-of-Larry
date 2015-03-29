package com.steftmax.temol.audio;

import static org.lwjgl.openal.AL10.AL_NO_ERROR;
import static org.lwjgl.openal.AL10.alGetError;

import org.lwjgl.openal.AL10;

/**
 * @author pieter3457
 *
 */
public abstract class Audio {

	protected int sourceID;


	protected void check() {
		int error = alGetError();
		if (error != AL_NO_ERROR) {
			System.out.println("OpenAL error: " + getALErrorString(error));
		}
	}
	
	public static String getALErrorString(int err) {
		  switch (err) {
		    case AL10.AL_NO_ERROR:
		      return "AL_NO_ERROR";
		    case AL10.AL_INVALID_NAME:
		      return "AL_INVALID_NAME";
		    case AL10.AL_INVALID_ENUM:
		      return "AL_INVALID_ENUM";
		    case AL10.AL_INVALID_VALUE:
		      return "AL_INVALID_VALUE";
		    case AL10.AL_INVALID_OPERATION:
		      return "AL_INVALID_OPERATION";
		    case AL10.AL_OUT_OF_MEMORY:
		      return "AL_OUT_OF_MEMORY";
		    default:
		      return "No such error code";
		  }
		}



}
