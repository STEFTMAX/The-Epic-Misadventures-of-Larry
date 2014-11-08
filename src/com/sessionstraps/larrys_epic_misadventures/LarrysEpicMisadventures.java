package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.level.Level;
import com.sessionstraps.game_engine.render.Game;
import com.sessionstraps.game_engine.resources.ResourceManager;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.render.Game#loadLevel(com.sessionstraps
	 * .game_engine.level.Level)
	 */
	@Override
	public void loadLevel(Level level) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Game#displaySplash()
	 */
	@Override
	public void displaySplash() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Game#createLevel()
	 */
	@Override
	public Level createLevel() {
		Level lvl = new Level(null);
		ResourceManager rm = new ResourceManager(lvl);
		lvl.addLevelObject(new Larry(0, 0, new KeyboardInput(window)));
		rm.getResources();
		rm.loadResources(true);
		return lvl;
	}

}
