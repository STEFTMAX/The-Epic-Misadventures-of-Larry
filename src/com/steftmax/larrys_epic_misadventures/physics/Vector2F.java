package com.steftmax.larrys_epic_misadventures.physics;

/**
 * @author pieter3457
 *
 */
public class Vector2F {
	public float x, y;

	public Vector2F(float x, float y) {
		this.x = x;
		this.y = y;
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
	
	public float length() {
		return (float) Math.sqrt((x*x) + (y*y));
	}

}
