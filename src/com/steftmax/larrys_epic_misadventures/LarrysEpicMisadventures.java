package com.steftmax.larrys_epic_misadventures;

import com.steftmax.larrys_epic_misadventures.draw.Camera;
import com.steftmax.larrys_epic_misadventures.draw.Drawer;
import com.steftmax.larrys_epic_misadventures.draw.Window;
import com.steftmax.larrys_epic_misadventures.entity.Larry;
import com.steftmax.larrys_epic_misadventures.entity.MockEntity;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.map.MapData;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources;
import com.steftmax.larrys_epic_misadventures.update.Updater;

public class LarrysEpicMisadventures extends Game {

	private static final String NAME = "Larry's epic misadventures";
	public int width = 800;
	public int height = 400;
	private KeyboardInput ki;
	private MouseInput mi;
	private Camera camera;
	private Level level;
	private LevelResources currentlyLoaded;
	private Drawer drawer;
	private Updater updater;

	//Private constructor to disable construction elsewhere than in main method.
	private LarrysEpicMisadventures() {}
	
	public static void main(String[] args) {
		new LarrysEpicMisadventures().start();
	}

	@Override
	public void init() {

		Window window = new Window(width, height, NAME, null);
		this.ki = new KeyboardInput();
		this.mi = new MouseInput(false);
		this.camera = new Camera(width, height, mi);
		
		long time1 = System.nanoTime();
		level = createLevel();
		System.out.println((System.nanoTime() - time1) / 1000000000f);
		
		drawer = new Drawer(level, window, camera);
		updater = new Updater(ki, mi, level);
		
		setup(1d, true);
	}
	
	@Override
	public void destroy() {
		currentlyLoaded.unload();
	}

	public Level createLevel() {
		currentlyLoaded = new LevelResources();
		currentlyLoaded.load();
		int[][] mapStructure = {
				{1,2,2,3},
				{4,5,5,6},
				{4,5,5,6},
				{4,5,5,6}
		};
		
		MapData data = new MapData(mapStructure, 32, 32);
		TiledMap map = new TiledMap(data);
		Level lvl = new Level();
		
		
		
		Larry larry = new Larry(0, 0, ki, mi, currentlyLoaded);
		lvl.addLevelObject(larry);
		lvl.addLevelObject(map);
		lvl.addLevelObject(new MockEntity(40, 0));
		camera.lock(larry.newPos);
		return lvl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {

		updater.update(delta);
		

		drawer.draw();
	}

}
