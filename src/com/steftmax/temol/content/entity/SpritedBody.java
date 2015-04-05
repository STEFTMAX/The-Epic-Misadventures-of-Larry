package com.steftmax.temol.content.entity;

import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.physics.RigidBody;

/**
 * @author pieter3457
 *
 */
public abstract class SpritedBody extends RigidBody{

	protected Sprite sprite;
	/**
	 * @param id
	 * @param mass
	 * @param inertia
	 * @param friction
	 * @param drag
	 */
	public SpritedBody(int id, float mass, float inertia, float friction,
			float drag) {
		super(id, mass, inertia, friction, drag);
	}
	
	/* (non-Javadoc)
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	@Override
	public void update(float delta) {
		
	}

}
