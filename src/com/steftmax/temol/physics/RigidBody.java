package com.steftmax.temol.physics;

import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.render.Updatable;

/**
 * @author pieter3457
 *
 */
public abstract class RigidBody implements Updatable{
	
	
	public RigidBody(int id, float mass, float inertia, float friction, float drag) {
		this.id = id;
		this.mass = mass;
		this.inertia =inertia;
		this.friction = friction;
		this.drag = drag;
	}
	
	public int id;
	public float mass, inertia, friction, drag;
	
	public float rotation = 0f;
	public Vector2 position = new Vector2();
	
	public float angularVelocity = 0f;
	public Vector2 linearVelocity = new Vector2();
	
	public Vector2 acceleration = new Vector2();
	
	public float torque = 0f;
	public Vector2 force = new Vector2();
	
	
	
	public void addForce(Vector2 relativePosition, Vector2 force) {
		
		this.force.add(force);
		torque += relativePosition.crossProduct(force);
	}
	
	public void addForce(Vector2 force) {
		addForce(force.x, force.y);
	}
	
	public void addForce(float dx, float dy) {
		force.add(dx, dy);
	}

	// TODO 
	public boolean onGround() {
		return false;
	}
}
