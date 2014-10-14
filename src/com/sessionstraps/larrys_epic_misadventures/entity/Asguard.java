package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sessionstraps.game_engine.entity.MovingEntity;
import com.sessionstraps.game_engine.render.DrawPriority;
import com.sessionstraps.game_engine.render.Drawable;
import com.sessionstraps.game_engine.render.Renderable;
import com.sessionstraps.game_engine.resources.SpriteManager;

public class Asguard extends MovingEntity implements Renderable, Drawable {

	public Asguard(float x, float y, SpriteManager sm) {
		super(x, y, sm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(pos.getRoundedX()- 40, pos.getRoundedY() -40, 40, 40);

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

	@Override
	public DrawPriority getPriority() {
		return DrawPriority.ENEMY;
	}

}
