package com.steftmax.larrys_epic_misadventures.physics;

import java.awt.Rectangle;

/**
 * @author pieter3457
 *
 */
public interface Collidable {
	
	public Rectangle getHitbox();
	public void setPreviousPosition();
}
