package com.steftmax.larrys_epic_misadventures.math;

/**
 * @author pieter3457
 * This class represents a vector, that can be used as position,
 * dimension, or for any other purpose.
 */
public class Vector2F {
	public float x = 0, y = 0;

	public Vector2F(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 */
	public Vector2F() {
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void substract(Vector2F v) {
		this.x -= v.x;
		this.y -= v.y;
	}

	public void substract(Vector2F v, float scale) {
		this.x -= v.x * scale;
		this.y -= v.y * scale;
	}

	public void add(float x, float y, float scale) {

		this.x += x * scale;
		this.y += y * scale;
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public void add(Vector2F v) {
		add(v.x, v.y);
	}

	public void add(Vector2F v, float scale) {
		add(v.x, v.y, scale);
	}

	public void multiply(Vector2F v) {
		this.x *= v.x;
		this.y *= v.y;
	}
	public void set(Vector2F v) {
		set(v.x, v.y);
	}
	
	public float toLength() {
		return (float) Math.sqrt((x*x) + (y*y));
	}

}
