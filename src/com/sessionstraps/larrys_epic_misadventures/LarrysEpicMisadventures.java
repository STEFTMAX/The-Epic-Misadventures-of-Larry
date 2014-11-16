package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.Game;
import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.input.MouseInput;
import com.sessionstraps.game_engine.level.Level;
import com.sessionstraps.game_engine.map.MapData;
import com.sessionstraps.game_engine.map.MapDataLoader;
import com.sessionstraps.game_engine.map.TiledMap;
import com.sessionstraps.game_engine.render.Camera;
import com.sessionstraps.game_engine.render.Updatable;
import com.sessionstraps.game_engine.render.Window;
import com.sessionstraps.game_engine.resources.ResourceManager;
import com.sessionstraps.game_engine.sprite.SpriteSheet;
import com.sessionstraps.larrys_epic_misadventures.entity.Larry;

public class LarrysEpicMisadventures extends Game {

	private static final String NAME = "Larry's epic misadventures";
	public int width = 800;
	public int height = 400;
	public int maxfps = 600;
	private KeyboardInput ki;
	private MouseInput mi;
	private Camera camera;
	private Level level;

	public static void main(String[] args) {
		new LarrysEpicMisadventures().start();
	}

	@Override
	public void init() {

		Window window = new Window(width, height, NAME, null);
		this.ki = new KeyboardInput();
		this.mi = new MouseInput(false);
		this.camera = new Camera(width, height, mi);
		level = createLevel();
		setup(window, camera, 60, 1d, ki, mi);
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
		// TODO Auto-generated method stub
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

	public Level createLevel() {
		SpriteSheet sheet = new SpriteSheet("tiles.png", 7, 2, 0);
		
		int[][] mapStructure = {
				{0,1,2,3,4,5,1,1,1,1,1,1,2}
		};
		
		
		MapData data = new MapData(mapStructure, sheet.getImageWidth(), sheet.getImageHeight());
		//MapDataLoader.writeMapData(data, "C:\\Users\\pieter\\Desktop\\test1");
		TiledMap map = new TiledMap(sheet, data );
		map.load();
		
		Level lvl = new Level(map);
		ResourceManager rm = new ResourceManager(lvl);

		Larry larry1 = new Larry(0, 0, ki, mi);
		lvl.addLevelObject(larry1);
		camera.lock(larry1.getLockingPosition());
		// lvl.addLevelObject(new AxisZeroPoint(0, 0));
		rm.getResources();
		rm.loadResources(true);
		return lvl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		for (Object cur : level.getLevelObjects()) {
			if (cur instanceof Updatable) {
				((Updatable) cur).update(delta);
			}
		}

		drawer.draw(level.getLevelObjects());
	}

}
