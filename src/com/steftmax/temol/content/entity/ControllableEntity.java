/**
 * @author pieter3457
 *
 */
package com.steftmax.temol.content.entity;

import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;

public abstract class ControllableEntity extends Entity {

	protected final KeyboardInput ki;
	protected final MouseInput mi;

	public ControllableEntity(float mass, float inertia, float friction, float drag, int maxHP, MouseInput mi, KeyboardInput ki) {
		super(mass, inertia, friction, drag, maxHP);
		this.ki = ki;
		this.mi = mi;
	}

}