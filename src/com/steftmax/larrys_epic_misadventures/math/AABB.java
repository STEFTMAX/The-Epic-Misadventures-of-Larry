package com.steftmax.larrys_epic_misadventures.math;

/**
 * Represents an integer AABB (axis aligned boundary box).
 * 
 * @author pieter3457
 *
 */
public class AABB {
	public int x, y, width, height;

	public AABB(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public AABB() {
		this(0,0,0,0);
	}

	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setPostion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean containsPoint(int x, int y) {
		if (this.x <= x) {
			if (this.x + width >= x) {
				if (this.y <= y) {
					if (this.y + height >= y) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean collides(int x, int y, int width, int height) {
		if (this.x < x + width) {
			if (x < this.x + this.width) {
				if (this.y < y + height) {
					if (y < this.y + this.height) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean collides(AABB otherBox) {
		
		return collides(otherBox.x, otherBox.y, otherBox.width, otherBox.height);

	}
	
	public void setBounds(int x, int y, int width, int height) {
		setPostion(x, y);
		setDimensions(width, height);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "x: " + x + " y: " + y + " width: " + width + " height: " + height;
	}
}
