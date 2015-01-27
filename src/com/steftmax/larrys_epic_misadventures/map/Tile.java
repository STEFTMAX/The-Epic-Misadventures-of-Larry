package com.steftmax.larrys_epic_misadventures.map;

import java.awt.Rectangle;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.draw.GLGraphics;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 *
 */
public class Tile implements Drawable{
	public TileType type;
	public final int x, y;

	public Tile(TileType type, int x, int y) {
		this.type = type;
		this.x= x;
		this.y = y;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(x,y,type.width, type.height);
	}
	
	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Drawable#draw(com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority)
	 */
	@Override
	public void draw() {
		type.sprite.draw(x,y);
	}
	
	public boolean hasSolid() {
		return type.hasSolid;
	}
}
