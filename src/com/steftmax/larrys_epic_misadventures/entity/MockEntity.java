package com.steftmax.larrys_epic_misadventures.entity;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;

/**
 * @author pieter3457
 *
 */
public class MockEntity extends Entity {

	public MockEntity(float x, float y) {
		super(x, y);
		hitbox = new Rectangle((int) newPos.x, (int) newPos.y, 180, 180);
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
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(hitbox.x, hitbox.y);
		GL11.glVertex2f(hitbox.x + hitbox.width, hitbox.y);
		GL11.glVertex2f(hitbox.x + hitbox.width, hitbox.y + hitbox.height);
		GL11.glVertex2f(hitbox.x, hitbox.y + hitbox.height);
		GL11.glEnd();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.render.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.physics.Collidable#setPreviousPosition
	 * ()
	 */
	@Override
	public void setPreviousPosition() {
		// TODO Auto-generated method stub

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

}
