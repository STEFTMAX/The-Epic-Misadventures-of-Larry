package com.steftmax.larrys_epic_misadventures;

import com.steftmax.larrys_epic_misadventures.draw.Window;
import com.steftmax.larrys_epic_misadventures.entity.Larry;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.map.MapData;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.resource.GameResources;
import com.steftmax.larrys_epic_misadventures.resource.ResourceManager;
import com.steftmax.larrys_epic_misadventures.state.MenuState;

public class LarrysEpicMisadventures extends Game {

	private static final String NAME = "Larry's epic misadventures";
	public int width = 1280;
	public int height = 720;
	private KeyboardInput ki;
	private MouseInput mi;
	private Level level;
	private ResourceManager currentlyLoaded;

	// Private constructor to disable construction elsewhere than in main
	// method.
	private LarrysEpicMisadventures() {
	}

	public static void main(String[] args) {
		new LarrysEpicMisadventures().start();
	}

	@Override
	public void init() {

		long time1 = System.nanoTime();

		Window window = new Window(width, height, NAME, null);
		this.ki = new KeyboardInput();
		this.mi = new MouseInput(false , 1f);
//		Level level = createLevel();
//		GameState gs = new GameState(this, level, mi, ki);
		MenuState ms = new MenuState(this, mi, ki);
		
		
		System.out.println("Loading took " + (System.nanoTime() - time1)
				/ 1000000000f + " seconds.");
		setup(window, 1d, false, 1000000000L / 20L, ms);
	}

	@Override
	public void destroy() {
		currentlyLoaded.unload();
	}

	public Level createLevel() {
		currentlyLoaded = new GameResources();
		currentlyLoaded.load();
		int[][] mapStructure = {

		{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 2, 2, 3, 0 }, { 0, 4, 5, 5, 6, 0 },
				{ 0, 4, 5, 5, 6, 0 }, { 0, 4, 5, 5, 6, 0 } };

		MapData data = new MapData(mapStructure, 32, 32);
		TiledMap map = new TiledMap(data);
		Level lvl = new Level(currentlyLoaded);

		Larry larry = new Larry(map, 32, 34, ki, mi, currentlyLoaded);
		lvl.setPlayer(larry);
		lvl.setMap(map);
		// for (int i = 0; i < 32; i ++) {
		// lvl.addLevelObject(new MockEntity());
		// }
		return lvl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
	}

}
