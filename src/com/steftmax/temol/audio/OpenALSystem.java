package com.steftmax.temol.audio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * @author pieter3457
 *
 */
public class OpenALSystem {
	
	private final static int MAXSOURCES = 16;
	private IntBuffer sources = createIntBuffer(MAXSOURCES);
	
	
	public IntBuffer getSource(){
		
	}
	
	public OpenALSystem(){
		
	}

	
	public static IntBuffer createIntBuffer(int size) {
		ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
		temp.order(ByteOrder.nativeOrder());
		return temp.asIntBuffer();
	}
}
