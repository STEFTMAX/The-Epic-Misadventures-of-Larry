package com.steftmax.temol.render.state;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.graphics.SpriteBatch;

/**
 * @author pieter3457
 *
 */
public class MapEditorState extends State {

	/**
	 * @param game
	 */
	public MapEditorState(Game game) {
		super(game);
		Display.setVSyncEnabled(true);
	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.render.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		// TODO Auto-generated method stub

	}

}
