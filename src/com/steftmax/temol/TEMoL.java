package com.steftmax.temol;

import com.steftmax.temol.render.state.MenuState;

public class TEMoL extends Game {

	private static final String NAME = "The epic misadventures of Larry";

	public TEMoL() {
		super(1f / 20f);
	}

	public static void main(String[] args) {
		new TEMoL().run();
	}

	@Override
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.Game#start()
	 */
	@Override
	public void start() {
		changeState(null, MenuState.class);
	}

}
