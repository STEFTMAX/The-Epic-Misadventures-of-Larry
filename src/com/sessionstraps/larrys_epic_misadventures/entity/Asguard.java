package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Graphics2D;

import com.sessionstraps.game_engine.entity.MovingEntity;
import com.sessionstraps.game_engine.entity.enemy.Enemy;
import com.sessionstraps.game_engine.resources.SpriteManager;

public class Asguard extends MovingEntity implements Enemy{

	public Asguard(float x, float y, SpriteManager sm) {
		super(x, y, sm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

}
