package com.steftmax.larrys_epic_misadventures;

import com.steftmax.larrys_epic_misadventures.content.Level;
import com.steftmax.larrys_epic_misadventures.content.entity.Larry;
import com.steftmax.larrys_epic_misadventures.content.map.old.MapData;
import com.steftmax.larrys_epic_misadventures.content.map.old.TiledMap;
import com.steftmax.larrys_epic_misadventures.render.state.MenuState;
import com.steftmax.larrys_epic_misadventures.resource.GameResources;

public class LarrysEpicMisadventures extends Game {

	

	private static final String NAME = "Larry's epic misadventures";
	
	

	public LarrysEpicMisadventures() {
		super(1000000000L / 20L);
	}
	

	public static void main(String[] args) {
		new LarrysEpicMisadventures().run();
	}

	@Override
	public void destroy() {
	}

	public Level createLevel() {
		GameResources res = new GameResources();
		res.load();

		int[][] mapStructure = {

		{ 0, 0, 0, 5, 6, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 1, 2, 2, 3, 0 }, { 0, 4, 5, 5, 6, 0 },
				{ 0, 4, 5, 5, 6, 0 }, { 0, 4, 5, 5, 6, 0 } };

		MapData data = new MapData(mapStructure, 32, 32);
		TiledMap map = new TiledMap(data);
		Level lvl = new Level(res);

		Larry larry = new Larry(map, 32, 34,  keyboardInput, mouseInput, res);
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


	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.Game#start()
	 */
	@Override
	public void start() {
		changeState(new MenuState(this));
	}

}
