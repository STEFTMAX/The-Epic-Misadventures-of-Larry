package com.steftmax.larrys_epic_misadventures.resource;

import java.util.HashMap;
import java.util.Map;

import com.steftmax.larrys_epic_misadventures.map.TileType;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.sprite.animation.PlaySequence;
import com.steftmax.larrys_epic_misadventures.sprite.animation.SwingPlaySequence;

/**
 * @author pieter3457
 *
 */
public class LevelResources implements Loadable {
	
	
	private boolean isLoaded = false;
	private Map <Resource, Loadable> loadedResources = new HashMap <Resource, Loadable>();
	
	private interface Resource {
		Loadable getLoadable();
	}
	public enum Animations implements Resource{
		
		LARRY_WALKING("gfx/larry_walking.png", 13, 2, PlaySequence.REPEAT, 40, 0),
		LARRY_BREATHING("gfx/larry_breathing.png", 3, 1, new SwingPlaySequence(true), 2, 0),
		LARRY_JUMPING("gfx/larry_jumping.png", 5, 5, PlaySequence.REPEAT, 40, 0),//TODO create custom sequence
		
		BLAZE_BREATHING("gfx/blaze_breathing.png", 3, 1, PlaySequence.REPEAT, 2, 0);
		
		public final Animation loadable;

		private Animations(String path, int x, int y, PlaySequence sequence, int fps, int skipLast) {
			this.loadable = new Animation(path, x, y, skipLast, sequence, fps);
		}
		
		@Override
		public Loadable getLoadable() {
			return loadable;
		}
	}
	
	public enum Tiles implements Resource{
		
		AIR(0, false, 32, 32),
		GRASSBLOCK_LEFT(1, true, "gfx/tile_grassblock_left.png"),
		GRASSBLOCK_MIDDLE(2, true, "gfx/tile_grassblock_middle.png"),
		GRASSBLOCK_RIGHT(3, true, "gfx/tile_grassblock_right.png"),
		DIRTBLOCK_LEFT(4, true, "gfx/tile_dirtblock_left.png"),
		DIRTBLOCK_MIDDLE(5, true, "gfx/tile_dirtblock_middle.png"),
		DIRTBLOCK_RIGHT(6, true, "gfx/tile_dirtblock_right.png"),
		PILLAR_TOP(7, true, "gfx/tile_pillar_top.png"),
		PILLAR_MIDDLE(8, true, "gfx/tile_pillar_middle.png"),
		PILLAR_BOTTOM(9, true, "gfx/tile_pillar_bottom.png"),
		STONE(10, true, "gfx/tile_stone.png"),
		STONE_PLATFORM(11, true, "gfx/tile_stone_platform.png");

		private final TileType loadable;
		private Tiles(int id, boolean isSolid, String path) {
			this.loadable = new TileType(id, isSolid, new Sprite(path));
		}
		
		private Tiles(int id, boolean isSolid, int width, int height ) {
			this.loadable = new TileType(id, width, height);
		}
		@Override
		public Loadable getLoadable() {
			return loadable;
		}
		
	}
	
	
	public LevelResources() {
		
	}
	
	@Override
	public void load() {
		
		
		loadResources(Animations.values());
		loadResources(Tiles.values());
		
	}
	/**
	 * @param animations 
	 * 
	 */
	private void loadResources(Resource[] set) {
			for (Resource r : set) {
			loadedResources.put(r, r.getLoadable());
			loadedResources.get(r).load();
		}
		isLoaded = true;
	}

	@Override
	public void unload() {
		
		for(Loadable l : loadedResources.values()) {
			l.unload();
		}
		
		loadedResources.clear();
		
		isLoaded = false;
	}
	public Object getResource(Resource r) {
		return loadedResources.get(r);
	}
	public boolean isLoaded() {
		return isLoaded;
	}

}
