package com.steftmax.larrys_epic_misadventures;

import com.steftmax.larrys_epic_misadventures.draw.Camera;
import com.steftmax.larrys_epic_misadventures.draw.Drawer;
import com.steftmax.larrys_epic_misadventures.draw.Window;
import com.steftmax.larrys_epic_misadventures.entity.Larry;
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
	private int loop = 0;

	//Private constructor to disable construction elsewhere than in main method.
	private LarrysEpicMisadventures() {}
	
	public static void main(String[] args) {
		new LarrysEpicMisadventures().start();
	}

	@Override
	public void init() {
		
		long time1 = System.nanoTime();
		
		Window window = new Window(width, height, NAME, null);
		this.ki = new KeyboardInput();
		this.mi = new MouseInput(false);
		this.camera = new Camera(width, height, mi);
		
		level = createLevel();
		
		drawer = new Drawer(level, window, camera);
		updater = new Updater(ki, mi, level);
		
		System.out.println("Loading took " +(System.nanoTime() - time1) / 1000000000f + " seconds.");
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


				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,1,2,2,3,0},
				{0,4,5,5,6,0},
				{0,4,5,5,6,0},
				{0,4,5,5,6,0}
		};
		
		MapData data = new MapData(mapStructure, 32, 32);
		TiledMap map = new TiledMap(data);
		Level lvl = new Level();
		
		
		
		Larry larry = new Larry(map, 32, 34, ki, mi, currentlyLoaded);
		lvl.addLevelObject(larry);
		lvl.addLevelObject(map);
//		for (int i = 0; i < 32; i ++) {
//			lvl.addLevelObject(new MockEntity());
//		}
		
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
		System.out.println("Looped! Loop: " + loop ++);
	}

}
