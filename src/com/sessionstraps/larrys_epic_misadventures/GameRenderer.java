
package com.sessionstraps.larrys_epic_misadventures;

import java.util.HashSet;

import com.Pieter3457.engine.entity.Entity;
import com.Pieter3457.engine.render.Renderable;


public class GameRenderer {

	public void render(long delta, HashSet<Entity> hashSet) {
		for (Entity cur : hashSet) {
			if (cur instanceof Renderable) {
				((Renderable) cur).render(delta);
			}
		}
	}

}
