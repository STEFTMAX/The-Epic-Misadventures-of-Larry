package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.render.Game;

public class LarrysEpicMisadventures extends Game {

	private static final String NAME = "Larry's epic misadventures";
	public int width = 800;
	public int height = 400;
	public int maxfps = 600;

	public static void main(String[] args) {
		new LarrysEpicMisadventures().start();
	}

	@Override
	public void init() {
		setup(NAME, width, height, maxfps, 1d);
	}

}
