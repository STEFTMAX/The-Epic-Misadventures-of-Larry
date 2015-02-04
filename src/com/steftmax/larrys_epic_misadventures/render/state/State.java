package com.steftmax.larrys_epic_misadventures.render.state;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.graphics.Drawable;
import com.steftmax.larrys_epic_misadventures.render.Updatable;

/**
 * @author pieter3457
 *
 */
public abstract class State implements Updatable, Drawable {
	protected final Game game;

	public State(Game game) {
		this.game = game;
	}
	
	public abstract void deleteResources();
}
