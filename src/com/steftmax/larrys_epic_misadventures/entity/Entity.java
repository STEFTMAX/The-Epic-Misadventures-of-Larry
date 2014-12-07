package com.steftmax.larrys_epic_misadventures.entity;

import java.awt.Rectangle;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.physics.Collidable;
import com.steftmax.larrys_epic_misadventures.physics.Point;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public abstract class Entity extends LevelObject implements Drawable, Updatable, Collidable {

	public final Point lastPos, newPos;
	protected float HP;
	protected Texture drawingTexture;
	protected Rectangle hitbox;

	public Entity(float x, float y) {
		newPos = new Point(x, y);
		lastPos = new Point(x, y);
		hitbox = new Rectangle(0,0,0,0);
	}
	

	public Rectangle getHitbox() {
		int width = drawingTexture.width;
		int height = drawingTexture.height;
		
		hitbox.setBounds((int) newPos.x, (int) newPos.y, drawingTexture.getWidth(), drawingTexture.getHeight());
		
		return hitbox;
	}



}
