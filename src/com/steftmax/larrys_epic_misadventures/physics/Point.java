package com.steftmax.larrys_epic_misadventures.physics;

public class Point {
	public float x;
	public float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public float getAbsoluteX() {
		return x;
	}

	public float getAbsoluteY() {
		return y;
	}

	public int getRoundedX() {
		return (int) x;
	}

	public int getRoundedY() {
		return (int) y;
	}

	public void setY(float y) {
		this.y = y;
	}
	

	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @param newPos
	 */
	public void set(Point newPoint) {
		this.x = newPoint.x;
		this.y = newPoint.y;
	}

}
