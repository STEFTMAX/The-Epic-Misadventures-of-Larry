package com.steftmax.larrys_epic_misadventures.math;


/**
 * @author pieter3457
 *
 */
public class AABB {
	public Vector2F position;
	public int width, height;
	
	public AABB(float x, float y, int width, int height) {
		this(new Vector2F(x, y), width, height);
	}
	
	public AABB(Vector2F position, int width, int height) {
		this.position=  position;
		this.width = width;
		this.height = height;
	}
	
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void setPostion(float x, float y) {
		position.set(x, y);
	}
	
	
	//TODO performance decision
	public boolean collides(AABB otherBox) {
		
		float x1 = position.x;
		float y1 = position.y;
		float x2 = otherBox.position.x;
		float y2 = otherBox.position.y;
		
		if (x1 < x2 + otherBox.width) {
			if (x2 < x1 + width) {
				if (y1 < y2 + otherBox.height) {
					if (y2 < y1 + height) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
