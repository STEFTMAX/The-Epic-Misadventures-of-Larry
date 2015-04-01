package com.steftmax.temol.content.map.old;

import java.awt.Rectangle;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;

/**
 * @author pieter3457
 *
 */
public class Tile implements Drawable {
	public TileType type;
	public final int x, y;
	private Sprite s;

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		s = new Sprite(type.sprite, x, y);
	}

	public Rectangle getHitbox() {
		return new Rectangle(x, y, type.width, type.height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.draw.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.draw.Drawer.DrawPriority)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		s.draw(batch);
	}

	public boolean hasSolid() {
		return type.hasSolid;
	}
}
