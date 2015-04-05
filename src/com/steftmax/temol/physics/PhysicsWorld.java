package com.steftmax.temol.physics;

import java.util.ArrayList;

import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.render.Updatable;

/**
 * @author pieter3457
 *
 */
public class PhysicsWorld implements Updatable {
	ArrayList<RigidBody> bodies = new ArrayList<RigidBody>();
	Vector2 gravitation = new Vector2(0, -9.81f);

	public PhysicsWorld() {

	}

	
	public void addBody(RigidBody body) {
		
		bodies.add(body);
		body.id = bodies.indexOf(body);
		
	}
	
	public void removeBody(RigidBody body) {
		
		bodies.remove(body);
		body.id = -1;
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	@Override
	public void update(float delta) {
		for (RigidBody body : bodies) {
			if (body.mass > 0) {
				if (!body.onGround()) {
					body.force.add(gravitation, body.mass);
				} else {
					
				}
			}
			

			body.update(delta);
			
			body.acceleration.set(body.force, 1/body.mass);
			float angularAcceleration = body.torque / body.inertia;
			
			body.linearVelocity.add(body.acceleration, .5f * TimeScaler.nanosToSecondsF(delta));
			body.angularVelocity += angularAcceleration* .5f * TimeScaler.nanosToSecondsF(delta);
			
			body.position.add(body.linearVelocity,TimeScaler.nanosToSecondsF(delta));
			body.rotation += body.angularVelocity * TimeScaler.nanosToSecondsF(delta);
			
			body.linearVelocity.add(body.acceleration, .5f* TimeScaler.nanosToSecondsF(delta));
			body.angularVelocity += angularAcceleration* .5f * TimeScaler.nanosToSecondsF(delta);
			
			body.torque = 0f;
			body.force.reset();
		}
	}
}
