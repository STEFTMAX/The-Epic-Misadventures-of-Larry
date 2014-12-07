/**
 * @author pieter3457
 *
 */
package com.steftmax.larrys_epic_misadventures.entity;

import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;

public abstract class ControllableEntity extends Entity {

	protected final KeyboardInput ki;
	protected final MouseInput mi;

	public ControllableEntity(float x, float y, KeyboardInput ki, MouseInput mi) {
		super(x, y);
		this.ki = ki;
		this.mi = mi;
	}

}