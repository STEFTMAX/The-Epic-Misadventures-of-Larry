package com.steftmax.larrys_epic_misadventures.state;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

/**
 * @author pieter3457
 *
 */
public abstract class State implements Updatable, Drawable {
	
	protected final MouseInput mi;
	protected final KeyboardInput ki;
	
	public State(MouseInput mi, KeyboardInput ki) {

		this.mi = mi;
		this.ki = ki;
	}
}
