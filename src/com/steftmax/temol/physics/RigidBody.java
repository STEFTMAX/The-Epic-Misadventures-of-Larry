package com.steftmax.temol.physics;

import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.Updatable;

/**
 * @author pieter3457
 *
 */
public abstract class RigidBody implements Updatable{
	public float mass, drag, airresistance;
	public Vector2 position;
	public Vector2 velocity;
	public Vector2 acceleration;
	public Vector2 force;
	
	/* (non-Javadoc)
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		acceleration.set(force, 1/mass);
		velocity.add(acceleration, .5f);
		position.add(velocity);
		velocity.add(acceleration, .5f);
	}
	
	public void addForce(Vector2 force) {
		addForce(force.x, force.y);
	}
	
	public void addForce(float x, float y) {
		force.add(x, y);
	}
}
