package com.sessionstraps.larrys_epic_misadventures.manager;

import java.util.HashSet;

import com.sessionstraps.game_engine.entity.Entity;

public class EntityManager {

	private HashSet<Entity> entities = new HashSet<Entity>();
	
	public HashSet<Entity> getEntities() {
		return entities;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

}
