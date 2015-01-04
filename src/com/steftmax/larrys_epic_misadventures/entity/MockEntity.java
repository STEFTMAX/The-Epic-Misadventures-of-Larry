package com.steftmax.larrys_epic_misadventures.entity;

import java.awt.Rectangle;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;
import com.steftmax.larrys_epic_misadventures.physics.Velocity;

/**
 * @author pieter3457
 *
 */
public class MockEntity extends Entity {
	private static final Random r = new Random();
	private final Velocity vel;
	
	private static final int WIDTH = 32, HEIGHT = WIDTH;

	public MockEntity() {
		super(r.nextInt(1024), r.nextInt(1024), 93, 45);
		vel = randomVel(12);
		hitbox = new Rectangle((int) newPos.x, (int) newPos.y, WIDTH, HEIGHT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.render.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.render.Drawer.DrawPriority)
	 */
	@Override
	public void draw(DrawPriority dp) {
		if (dp != DrawPriority.BACK);
		GL11.glColor3f(0, 0, 0);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(hitbox.x, hitbox.y);
		GL11.glVertex2f(hitbox.x + hitbox.width, hitbox.y);
		GL11.glVertex2f(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
		GL11.glVertex2f(hitbox.x, hitbox.y + hitbox.height);
		GL11.glEnd();

	}
	
	private Velocity randomVel(int variaton) {
		int dx = r.nextInt(variaton) + 1;
		int dy = r.nextInt(variaton) + 1;
		if (r.nextBoolean()) {
			dx = -dx;
		}
		if (r.nextBoolean()) {
			dy = -dy;
		}
		return new Velocity(dx, dy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		hitbox.x += vel.dx;
		hitbox.y += vel.dy;
		if (hitbox.x > 1023 || hitbox.x < 0) {
			vel.dx = - vel.dx;
		}
		if (hitbox.y > 1023 || hitbox.y < 0) {
			vel.dy = - vel.dy;
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.entity.Entity#getHitbox()
	 */
	@Override
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.entity.Entity#onCollide(com.steftmax.larrys_epic_misadventures.entity.Entity)
	 */
	@Override
	public void onCollide(Entity collideEnt) {
		vel.dx = -vel.dx;

		vel.dy = -vel.dy;
	}

}
