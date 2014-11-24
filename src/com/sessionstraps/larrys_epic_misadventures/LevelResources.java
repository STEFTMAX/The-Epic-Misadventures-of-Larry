package com.sessionstraps.larrys_epic_misadventures;

import java.util.HashMap;
import java.util.Map;

import com.sessionstraps.game_engine.resources.Loadable;
import com.sessionstraps.game_engine.resources.Texture;
import com.sessionstraps.game_engine.sprite.Sprite;
import com.sessionstraps.game_engine.sprite.animation.Animation;
import com.sessionstraps.game_engine.sprite.animation.PlaySequence;
import com.sessionstraps.game_engine.sprite.animation.SwingPlaySequence;

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
		
		LARRY_WALKING("img/larry_walking.png", 13, 2, PlaySequence.REPEAT, 50, 0),
		LARRY_BREATHING("img/larry_breathing.png", 3, 1, new SwingPlaySequence(true), 2, 0);
		
		public final Loadable loadable;

		private Animations(String path, int x, int y, PlaySequence sequence, int fps, int skipLast) {
			this.loadable = new Animation(path, x, y, skipLast, sequence, fps);
		}
		
		public Loadable getLoadable() {
			return loadable;
		}
	}
	
	public enum Textures implements Resource{
		GRASSBLOCK_LEFT("img/tile_grassblock_left.png"),
		GRASSBLOCK_MIDDLE("img/tile_grassblock_middle.png"),
		GRASSBLOCK_RIGHT("img/tile_grassblock_right.png"),
		DIRTBLOCK_LEFT("img/tile_dirtblock_left.png"),
		DIRTBLOCK_MIDDLE("img/tile_dirtblock_middle.png"),
		DIRTBLOCK_RIGHT("img/tile_dirtblock_right.png"),
		PILLAR_TOP("img/tile_pillar_top.png"),
		PILLAR_MIDDLE("img/tile_pillar_middle.png"),
		PILLAR_BOTTOM("img/tile_pillar_bottom.png");

		private Texture loadable;
		private Textures(String path) {
			this.loadable = new Texture(new Sprite(path));
		}
		@Override
		public Loadable getLoadable() {
			return loadable;
		}
		
	}
	
	
	public LevelResources(){
		
	}
	
	@Override
	public void load() {
		
		
		loadResources(Animations.values());
	
		
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
		
		isLoaded = false;
	}
	public Object getResource(Resource r) {
		return loadedResources.get(r);
	}
	public boolean isLoaded() {
		return isLoaded;
	}

}
