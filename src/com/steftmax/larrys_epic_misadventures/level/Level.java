package com.steftmax.larrys_epic_misadventures.level;

import java.util.HashSet;
import java.util.Set;

import com.steftmax.larrys_epic_misadventures.map.TiledMap;

public class Level {

	private Set<LevelObject> objects = new HashSet<LevelObject>();
	public TiledMap map;


	public Set<LevelObject> getLevelObjects() {
		return objects;
	}

	public void addLevelObject(LevelObject obj) {
		obj.setLevel(this);

		
		if (obj instanceof TiledMap) {
			map = (TiledMap) obj;
		}
		
		objects.add(obj);
	}

	public void removeLevelObject(LevelObject obj) {
		obj.setLevel(null);
		if (obj instanceof TiledMap) {
			map = null;
		}
		objects.remove(obj);
	}
}