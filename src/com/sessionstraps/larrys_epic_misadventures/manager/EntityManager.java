package com.sessionstraps.larrys_epic_misadventures.manager;

import java.util.HashSet;

import com.sessionstraps.game_engine.entity.MovingEntity;

public class EntityManager {

	private HashSet<MovingEntity> entities = new HashSet<MovingEntity>();
	
	public HashSet<MovingEntity> getEntities() {
		return entities;
	}
	
	public void addEntity(MovingEntity entity) {
		entities.add(entity);
	}
	
	public void removeEntity(MovingEntity entity) {
		entities.remove(entity);
	}

}
