package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Set;

import com.sessionstraps.game_engine.entity.Entity;
import com.sessionstraps.game_engine.entity.enemy.Enemy;
import com.sessionstraps.game_engine.resources.Loadable;
import com.sessionstraps.game_engine.resources.ResourceManager;

public class AxisZeroPoint extends Entity implements Enemy{
	public AxisZeroPoint(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, 0);
	}

	@Override
	public void render(long delta) {}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.level.LevelObject#getNeededResources(java.util.Set)
	 */
	@Override
	public void getNeededResources(Set<Loadable> toAddTo) {}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.level.LevelObject#grabResources(com.sessionstraps.game_engine.resources.ResourceManager)
	 */
	@Override
	public void grabResources(ResourceManager rm) {}

}
