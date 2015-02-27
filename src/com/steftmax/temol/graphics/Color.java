package com.steftmax.temol.graphics;

/**
 * @author pieter3457
 *
 */
public class Color {
	public float red, green, blue, alpha;

	public Color(float red, float green, float blue, float alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
		normalize();
	}

	public void normalize() {
		if (red > 1f) {
			red = 1f;
		} else {
			if (red < 0f) {
				red = 0f;
			}
		}
		if (green > 1f) {
			green = 1f;
		} else {
			if (green < 0f) {
				green = 0f;
			}
		}
		if (blue > 1f) {
			blue = 1f;
		} else {
			if (blue < 0f) {
				blue = 0f;
			}
		}
		if (alpha > 1f) {
			alpha = 1f;
		} else {
			if (alpha < 0f) {
				alpha = 0f;
			}
		}
	}
}
