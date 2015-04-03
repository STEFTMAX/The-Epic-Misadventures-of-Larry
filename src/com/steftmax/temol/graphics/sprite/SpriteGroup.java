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
	private Vector2 spritePostion = new Vector2(),
			spriteOrigin = new Vector2();

	@Override
	public void draw(SpriteBatch batch) {
		for (Sprite sprite : sprites) {

			spritePostion.set(position.x + sprite.pos.x, position.y
					+ sprite.pos.y);

			spriteOrigin.set(sprite.origin);

			if (flipY) {
				spritePostion.x -= (sprite.width - origin.x * 2);
				spriteOrigin.x = sprite.width - spriteOrigin.x;
			}
			if (flipX) {
				spritePostion.y -= (sprite.height - origin.y * 2);
				spriteOrigin.y = sprite.height - spriteOrigin.y;
				
			}

			batch.draw(sprite, spritePostion.x, spritePostion.y, sprite.width,
					sprite.height, scaleX * sprite.scaleX, scaleY
							* sprite.scaleY, flipX != sprite.flipX,
					flipY != sprite.flipY, sprite.color, sprite.rotation,
					spriteOrigin.x, spriteOrigin.y);
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
	 * Replaces the oldsprite with the newsprite in the same place in the array.
	 * If the oldSprite isn't found, it isnt added.
	 * 
	 * @param oldSprite
	 * @param newSprite
	 * @return Whether or not the oldsprite is replaced.
	 */
	public boolean replaceSprite(Sprite oldSprite, Sprite newSprite) {
		final int index = sprites.indexOf(oldSprite);
		if (index < 0)
			return false;
		sprites.set(index, newSprite);
		return true;
	}

	public void setOrigin(Vector2 origin) {
		this.origin =origin;
		System.out.println(origin);
	}

}
