package com.steftmax.temol.math;

/**
 * @author pieter3457 This class represents a vector, that can be used as
 *         position, dimension, or for any other purpose.
 */
public class Vector2 {
	public float x = 0, y = 0;

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 */
	public Vector2() {
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void subtract(float x, float y) {
		this.x -= x;
		this.y -= x;
	}

	public void subtract(Vector2 v) {
		subtract(v.x, v.y);
	}

	public void subtract(Vector2 v, float scale) {
		subtract(v.x, v.y, scale);
	}

	public void subtract(float x, float y, float scale) {
		this.x -= x * scale;
		this.y -= y * scale;
	}

	public void add(float x, float y, float scale) {

		this.x += x * scale;
		this.y += y * scale;
	}

	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public void add(Vector2 v) {
		add(v.x, v.y);
	}

	public void add(Vector2 v, float scale) {
		add(v.x, v.y, scale);
	}

	public void multiply(Vector2 v) {
		this.x *= v.x;
		this.y *= v.y;
	}

	public void set(Vector2 v) {
		set(v.x, v.y);
	}

	public float toLength() {
		return (float) Math.sqrt((x * x) + (y * y));
	}

	public float dotProduct(Vector2 v) {
		return dotProduct(v.x, v.y);
	}

	private float dotProduct(float x, float y) {
		return this.x * x + this.y * y;
	}

	public void normalize() {
		final float length = toLength();
		if (length != 0) {
			x = x / length;
			y = y / length;
		}
	}
}
