package com.steftmax.larrys_epic_misadventures.update;

import java.util.HashSet;
import java.util.Set;

import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.physics.Collidable;
import com.steftmax.larrys_epic_misadventures.physics.Grid;

/**
 * @author pieter3457
 *
 */
public class Updater implements Updatable {
	
	private KeyboardInput ki;
	private MouseInput mi;
	private Level level;
	private Grid g = new Grid(64,1024,1024);
	
	private Set<Collidable> bodies = new HashSet<Collidable>();
	

	public Updater(KeyboardInput ki, MouseInput mi, Level level) {
		this.ki = ki;
		this.mi = mi;
		this.level = level;
	}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.render.Updatable#update(long)
	 */
	@Override
	public void update(final long delta) {
		
		ki.update(delta);
		mi.update(delta);

		Set<LevelObject> set = level.getLevelObjects();
		
		for (LevelObject obj : set) {

			if (obj instanceof Updatable) {
				((Updatable) obj).update(delta);
			}

			if (obj instanceof Collidable) {
				g.add((Collidable) obj);
			}
		}

		
		g.checkCollisions();
		g.clear();
	}

	public void changeLevel(Level level) {
		this.level = level;
	}
}
