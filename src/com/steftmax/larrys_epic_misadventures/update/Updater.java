package com.steftmax.larrys_epic_misadventures.update;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.steftmax.larrys_epic_misadventures.entity.Entity;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.math.QuadTree;
import com.steftmax.larrys_epic_misadventures.physics.CollisionChecker;

/**
 * @author pieter3457
 *
 */
public class Updater implements Updatable {

	private KeyboardInput ki;
	private MouseInput mi;
	private Level level;
	// TODO performance point 1st param
	public QuadTree g = new QuadTree(4, 1024, 1024, 5);
	private TiledMap theMap;
	private final List<Entity> returnObjects = new ArrayList<Entity>();

	public Updater(KeyboardInput ki, MouseInput mi, Level level) {
		this.ki = ki;
		this.mi = mi;
		this.level = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(final long delta) {

		g.clear();
		ki.update(delta);
		mi.update(delta);

		Set<LevelObject> set = level.getLevelObjects();

		for (LevelObject obj : set) {

			if (obj instanceof Updatable) {
				((Updatable) obj).update(delta);
			}

			if (obj instanceof Entity) {
				g.add((Entity) obj);
			}
			if (obj instanceof TiledMap) {
				theMap = (TiledMap) obj;
			}

		}
		// TODO PERFOMRANCE BITCH
		
		for (LevelObject obj : set) {
			if (obj instanceof Entity) {
				returnObjects.clear();
				Entity ent1 = (Entity) obj;
				g.retrieve(returnObjects, ent1);

				Rectangle box1 = ent1.getHitbox();
				for (Entity ent2 : returnObjects) {
					Rectangle box2 = ent2.getHitbox();

					if (CollisionChecker.boxCollides(box1.x, box1.y,
							box1.width, box1.height, box2.x, box2.y,
							box2.width, box2.height)) {
						ent1.onCollide(ent2);
						ent2.onCollide(ent1);
						break;
					}
				}
			}
		}
	}

	public void changeLevel(Level level) {
		this.level = level;
	}

}
