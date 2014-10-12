package com.Pieter3457.MiniJavaGame;

import com.Pieter3457.MiniJavaGame.entity.Larry;
import com.Pieter3457.MiniJavaGame.manager.EntityManager;
import com.Pieter3457.engine.input.EntityController;
import com.Pieter3457.engine.resources.SpriteManager;

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
		entManager.addEntity(new Larry(WIDTH / 2, HEIGHT, -90, 10, sm,
				new EntityController(gd)));

		new Thread(new GameUpdater(new GameRenderer(), gd, entManager)).start();
	}
}
