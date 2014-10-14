package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.input.EntityController;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;
import com.sessionstraps.larrys_epic_misadventures.manager.EntityManager;

public class Game implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static final String NAME = "Larry's epic misadventures";
	public static final int MAXFPS = 60;

	public static void main(String[] args) {
		new Thread(new Game()).start();
	}

	public Game() {
	}

	@Override
	public void run() {

		SpriteManager sm = new SpriteManager("/images//spritesheets.yml");
		sm.loadSprites();
		GameDrawer gd = new GameDrawer();
		EntityManager entManager = new EntityManager();
		entManager.addEntity(new Larry(WIDTH / 2, HEIGHT, 60, sm,
				new EntityController(gd.getCanvas())));

		new Thread(new GameUpdater(new GameRenderer(), gd, entManager)).start();
	}
}
