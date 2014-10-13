
package com.sessionstraps.larrys_epic_misadventures;

import java.util.HashSet;

import com.sessionstraps.game_engine.entity.MovingEntity;
import com.sessionstraps.game_engine.render.Renderable;


public class GameRenderer {

	public void render(long delta, HashSet<MovingEntity> hashSet) {
		for (MovingEntity cur : hashSet) {
			if (cur instanceof Renderable) {
				((Renderable) cur).render(delta);
			}
		}
	}

}
