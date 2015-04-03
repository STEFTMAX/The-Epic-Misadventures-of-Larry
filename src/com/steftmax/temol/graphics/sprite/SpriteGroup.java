package com.steftmax.temol.graphics.sprite;

import java.util.ArrayList;
import java.util.List;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.animation.Animation;
import com.steftmax.temol.math.Vector2;

/**
 * @author pieter3457 Represents a group of Sprites
 */
public class SpriteGroup implements Drawable {

	private List<Sprite> sprites = new ArrayList<Sprite>();
	Vector2 position = new Vector2();
	float scaleX = 1f, scaleY = 1f;
	boolean flipX = false, flipY = false;
	float rotation = 0f;
	Vector2 origin = new Vector2();

	public void setFlip(boolean x, boolean y) {
		flipX = x;
		flipY = y;
	}

	public SpriteGroup(float x, float y) {
		this.position = new Vector2(x, y);
	}

	public SpriteGroup(Vector2 position) {
		this.position = position;
	}

	public void setOrigin(float x, float y) {
		origin.set(x, y);
	}

	/**
	 * Adds a sprite to this group. It will be the last to draw.
	 * 
	 * @param sprite
	 *            The sprite to add.
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	/**
	 * Adds a sprite to this group at the specified index, where index 0 would
	 * be the first to draw.
	 * 
	 * @param sprite
	 *            The sprite.
	 * @param index
	 *            The index.
	 */
	public void addSprite(Sprite sprite, int index) {
		sprites.add(index, sprite);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics
	 * .SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		for (Sprite sprite : sprites) {
			batch.draw(sprite, sprite.pos.x + position.x, sprite.pos.y
					+ position.y, sprite.width, sprite.height, scaleX
					* sprite.scaleX, scaleY * sprite.scaleY,
					flipX != sprite.flipX, flipY != sprite.flipY, sprite.color,
					sprite.rotation, sprite.origin.x, sprite.origin.y);
		}

	}

	/**
	 * @param position
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	/**
	 * @param animation
	 */
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}

	/**
	 * Replaces the oldsprite with the newsprite in the same place in the array. If the oldSprite isn't found, it isnt added.
	 * @param oldSprite
	 * @param newSprite
	 * @return Whether or not the oldsprite is replaced.
	 */
	public boolean replaceSprite(Sprite oldSprite, Sprite newSprite) {
		final int index = sprites.indexOf(oldSprite);
		if (index < 0) return false;
		sprites.set(index, newSprite);
		return true;
	}

}
