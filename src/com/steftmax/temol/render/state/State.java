package com.steftmax.temol.render.state;

import com.steftmax.temol.Game;
import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.render.Updatable;

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
