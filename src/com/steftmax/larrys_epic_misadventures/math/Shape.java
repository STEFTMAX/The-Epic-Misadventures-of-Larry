package com.steftmax.larrys_epic_misadventures.math;


/**
 * @author pieter3457
 *
 */
public interface Shape {
	public AABB toAABB();
	public boolean collides(AABB box);
	//Methods when other shapes are created to be added here
}
