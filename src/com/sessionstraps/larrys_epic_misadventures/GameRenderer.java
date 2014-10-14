
package com.sessionstraps.larrys_epic_misadventures;

import java.util.HashSet;

import com.sessionstraps.game_engine.entity.Entity;
import com.sessionstraps.game_engine.render.Renderable;


public class GameRenderer {

	public void render(long delta, HashSet<Entity> entity) {
		for (Entity cur : entity) {
			if (cur instanceof Renderable) {
				((Renderable) cur).render(delta);
			}
		}
	}

}
