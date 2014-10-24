package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.level.Level;
import com.sessionstraps.game_engine.map.TiledMap;
import com.sessionstraps.game_engine.render.Camera;
import com.sessionstraps.game_engine.render.Drawer;
import com.sessionstraps.game_engine.render.Game;
import com.sessionstraps.game_engine.render.Window;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.larrys_epic_misadventures.entity.Asguard;
import com.sessionstraps.larrys_epic_misadventures.entity.AxisZeroPoint;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;

public class LarrysEpicMisadventures extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static final String NAME = "Larry's Epic Misadventures";
	public static final int MAXFPS = 600;

	public static void main(String[] args) {
		new Thread(new LarrysEpicMisadventures()).start();
	}
	@Override
	public void run() {

		SpriteManager sm = new SpriteManager("/images//spritesheets.yml");
		sm.loadSprites();
		Level entManager = new Level(new TiledMap(null));

		Larry larry = new Larry(0, 0, sm, ec);
		entManager.addLevelObject(larry);

		Asguard asguard = new Asguard(0, 0, sm);
		entManager.addLevelObject(asguard);

		entManager.addLevelObject(new AxisZeroPoint(0, 0, sm));

		new Thread(new Game(entManager, MAXFPS, 1, WIDTH, HEIGHT, NAME))
				.start();
	}
}
