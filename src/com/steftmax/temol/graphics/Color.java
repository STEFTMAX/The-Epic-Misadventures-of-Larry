package com.steftmax.temol.graphics;

/**
 * @author pieter3457
 *
 */
public class Color {
	public byte red, green, blue, alpha;

	public Color(float red, float green, float blue, float alpha) {
		red /= 2f;
		blue /= 2f;
		green /= 2f;
		alpha /= 2f;
	}
	
	public Color(int red, int green, int blue, int alpha) {
		this.red = (byte) red;
		this.green = (byte) green;
		this.blue = (byte) blue;
		this.alpha = (byte) alpha;
	}
}
