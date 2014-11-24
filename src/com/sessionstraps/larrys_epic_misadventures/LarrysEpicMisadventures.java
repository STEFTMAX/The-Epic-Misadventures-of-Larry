package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.game_engine.Game;
import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.input.MouseInput;
import com.sessionstraps.game_engine.level.Level;
import com.sessionstraps.game_engine.map.MapData;
import com.sessionstraps.game_engine.map.TiledMap;
import com.sessionstraps.game_engine.render.Camera;
import com.sessionstraps.game_engine.render.Updatable;
import com.sessionstraps.game_engine.render.Window;
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
	private LevelResources currentlyLoaded;

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
		setup(window, camera, 1d, ki, mi);
	}
	
	@Override
	public void destroy() {
		currentlyLoaded.unload();
	}

	public Level createLevel() {
		
		currentlyLoaded = new LevelResources();
		currentlyLoaded.load();
		int[][] mapStructure = {
				{0,1,6,2},
				{3,4,7,5},
				{3,4,4,5},
				{3,4,4,5}
		};
		
		
		TileManager tm = new TileManager(32,32);
		try {
			tm.load();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		MapData data = new MapData(mapStructure, tm.getTileWidth(), tm.getTileHeight());
		//MapDataLoader.writeMapData(data, "C:\\Users\\pieter\\Desktop\\test1");
		TiledMap map = new TiledMap(tm.getTiles(), data );
		//map.load();
		
		Level lvl = new Level();
		
		
		
		Larry larry = new Larry(0, 0, ki, mi, currentlyLoaded);
		lvl.addLevelObject(larry);
		lvl.addLevelObject(map);
		camera.lock(larry.getLockingPosition());
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
