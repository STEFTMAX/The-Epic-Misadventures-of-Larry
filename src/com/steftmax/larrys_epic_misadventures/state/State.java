package com.steftmax.larrys_epic_misadventures.state;

import com.steftmax.larrys_epic_misadventures.Game;
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
	protected final Game game;

	public State(Game game, MouseInput mi, KeyboardInput ki) {
		this.game = game;
		this.mi = mi;
		this.ki = ki;
	}
	
	public abstract void deleteResources();
}
