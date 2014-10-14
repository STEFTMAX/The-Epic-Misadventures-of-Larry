package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.entity.EntityManager;
import com.sessionstraps.game_engine.input.EntityController;
import com.sessionstraps.game_engine.render.GameDrawer;
import com.sessionstraps.game_engine.render.GameRenderer;
import com.sessionstraps.game_engine.render.GameUpdater;
import com.sessionstraps.game_engine.render.GameWindow;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;

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
		GameDrawer gd = new GameDrawer(new GameWindow(Game.WIDTH, Game.HEIGHT, Game.NAME));
		EntityManager entManager = new EntityManager();
		entManager.addEntity(new Larry(WIDTH / 2, HEIGHT, 60, sm,
				new EntityController(gd.getCanvas())));

		new Thread(new GameUpdater(new GameRenderer(), gd, entManager, MAXFPS)).start();
	}
}
