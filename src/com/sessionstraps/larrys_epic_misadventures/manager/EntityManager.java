package com.sessionstraps.larrys_epic_misadventures.manager;

import java.util.HashSet;

import com.Pieter3457.engine.entity.Entity;

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
