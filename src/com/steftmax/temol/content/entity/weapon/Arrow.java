package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.content.entity.Entity;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.resource.ResourceManager;

/**
 * @author pieter3457
 *
 */
public class Arrow extends Entity{
	Vector2 position, velocity;
	private Sprite sprite;

	public Arrow(float x, float y, float dx, float dy, ResourceManager rm) {
		super(9, 9, 9, 9, 9);
		this.position = new Vector2(x, y);
		this.velocity = new Vector2(dx, dy);
		this.sprite = new Sprite(rm.getTexture("gfx/arrow.png"));
	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	@Override
	public void update(float delta) {
		position.add(velocity, delta);
	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}
	

	
}
