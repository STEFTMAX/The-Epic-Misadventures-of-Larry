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

//	public void multiply(Matrix2 m) {
//		this.x = this.x * m.values[0] + this.y * m.values[1];
//		this.y = this.y * m.values[2] + this.x * m.values[3];
//	}
	
	public static void multiply(Matrix2 m, Vector2 v, Vector2 result) {

		result.x = v.x * m.values[0] + v.y * m.values[1];
		result.y = v.y * m.values[3] + v.x * m.values[2];
	}

	public void set(Vector2 v) {
		set(v.x, v.y);
	}

	public float lengthSquared() {
		return (x * x) + (y * y);
	}

	public float toLength() {
		return (float) Math.sqrt(lengthSquared());
	}

	public float dotProduct(Vector2 v) {
		return dotProduct(v.x, v.y);
	}

	public float dotProduct(float x, float y) {
		return dotProduct(this.x, this.y, x, y);
	}

	public static float dotProduct(Vector2 v1, Vector2 v2) {
		return dotProduct(v1.x, v1.y, v2.x, v2.y);
	}

	public static float dotProduct(float x1, float y1, float x2, float y2) {
		return x1 * x2 + y1 * y2;
	}

	public float crossProduct(Vector2 v) {
		return crossProduct(x, y, v.x, v.y);
	}

	public float crossProduct(float x, float y) {
		return crossProduct(this.x, this.y, x, y);
	}

	public static float crossProduct(Vector2 v1, Vector2 v2) {
		return crossProduct(v1.x, v1.y, v2.x, v2.y);
	}

	public static float crossProduct(float x1, float y1, float x2, float y2) {
		return x1 * y2 - y1 * x2;
	}

	public void normalize() {
		final float length = toLength();
		if (length != 0) {
			x = x / length;
			y = y / length;
		}
	}

	public void set(Vector2 v, float scale) {
		this.x = v.x * scale;
		this.y = v.y * scale;
	}

	public void reset() {
		this.x = 0;
		this.y = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector2 x = " + x + " y = " + y;
	}

}
