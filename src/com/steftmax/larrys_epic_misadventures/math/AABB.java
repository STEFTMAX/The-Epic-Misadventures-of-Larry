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
		if (position.x < otherBox.position.x + otherBox.width) {
			if (otherBox.position.x < position.x + width) {
				if (position.y < otherBox.position.y + otherBox.height) {
					if (otherBox.position.y < position.y + height) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
