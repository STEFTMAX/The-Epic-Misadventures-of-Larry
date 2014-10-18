package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.entity.UpdateObjectContainer;
import com.sessionstraps.game_engine.input.EntityController;
import com.sessionstraps.game_engine.render.Camera;
import com.sessionstraps.game_engine.render.GameDrawer;
import com.sessionstraps.game_engine.render.GameRenderer;
import com.sessionstraps.game_engine.render.GameUpdater;
import com.sessionstraps.game_engine.render.GameWindow;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.larrys_epic_misadventures.entity.Asguard;
import com.sessionstraps.larrys_epic_misadventures.entity.AxisZeroPoint;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;

public class Game implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static final String NAME = "Larry's Epic Misadventures";
	public static final int MAXFPS = 600;

	public static void main(String[] args) {
		new Thread(new Game()).start();
	}

	public Game() {
	}

	@Override
	public void run() {

		SpriteManager sm = new SpriteManager("/images//spritesheets.yml");
		sm.loadSprites();

		Camera cam = new Camera(null, WIDTH, HEIGHT);
		GameDrawer gd = new GameDrawer(new GameWindow(WIDTH, HEIGHT, NAME), cam);

		UpdateObjectContainer entManager = new UpdateObjectContainer();

		Larry larry = new Larry(0, 0, sm, new EntityController(
				gd.getCanvas()));
		entManager.addObject(larry);
		cam.lock(larry);

		Asguard asguard = new Asguard(0, 0, sm);
		entManager.addObject(asguard);
		
		entManager.addObject(new AxisZeroPoint(sm));

		new Thread(new GameUpdater(new GameRenderer(), gd, entManager, MAXFPS, 1d))
				.start();
	}
}
