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
	
	public boolean collides(AABB otherBox) {
		if (x < otherBox.x + otherBox.width) {
			if (otherBox.x < x + width) {
				if (y < otherBox.y + otherBox.height) {
					if (otherBox.y < y + height) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
