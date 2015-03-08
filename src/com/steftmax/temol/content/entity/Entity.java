package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.physics.RigidBody;
import com.steftmax.temol.render.Updatable;

public abstract class Entity extends RigidBody implements Drawable, Updatable {

	public final Vector2 lastPos, newPos, velocity;
	protected float HP;
	protected final int maxHP;
	protected Sprite sprite;
	public final AABB hitbox;
	protected TiledMap map;
	protected boolean isOnGround = false;
	

	public Entity(TiledMap map, float x, float y, final int mass, final int maxHP) {
		this.map = map;
		this.HP = maxHP;
		this.maxHP = maxHP;
		newPos = new Vector2(x, y);
		lastPos = new Vector2(x, y);
		velocity = new Vector2(0,0);
		hitbox = new AABB(0, 0, 0, 0);
		sprite = new Sprite();
	}

	protected void updateHitbox() {
		final int width = sprite.width;
		final int height = sprite.height;

		hitbox.setBounds((int) newPos.x, (int) newPos.y - height, width, height);
	}
	
	@Deprecated
	public AABB getHitbox() {
		return hitbox;
	}
}
