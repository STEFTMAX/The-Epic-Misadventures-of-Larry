package com.steftmax.larrys_epic_misadventures.draw;

import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author pieter3457
 *
 */
public class Window {
	
	public int width, height;

	public Window(final int width, final int height, final String name, final ByteBuffer[] icons) {
		
		this.width = width;
		this.height = height;
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(name);
			Display.setIcon(icons);
			Display.setResizable(false);
			Display.create();
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	@Deprecated
	public void update() {
		Display.update();
	}
	
	@Deprecated
	public boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
}
