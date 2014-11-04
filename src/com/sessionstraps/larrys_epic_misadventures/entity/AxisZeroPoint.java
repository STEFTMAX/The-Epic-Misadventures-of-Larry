package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Set;

import com.sessionstraps.game_engine.entity.Entity;
import com.sessionstraps.game_engine.entity.enemy.Enemy;
import com.sessionstraps.game_engine.resources.ResourceManager;

public class AxisZeroPoint extends Entity implements Enemy{
	public AxisZeroPoint(float x, float y, ResourceManager rm) {
		super(x, y, rm);
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, 0);
	}

	@Override
	public void render(long delta) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.level.LevelObject#getNeededResourses(java.util.Set)
	 */
	@Override
	public void getNeededResourses(Set<String> toPutTo) {
		// TODO Auto-generated method stub
		
	}

}
