package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.render.Updatable;

public abstract class Entity extends SpritedBody implements Drawable, Updatable {

	protected float HP;
	protected final int maxHP;
	public final AABB hitbox;
	protected TiledMap map;
	
	public Entity(float mass, float inertia, float friction, float drag, final int maxHP) {
		super(-1, mass, inertia, friction, drag);
		this.HP = maxHP;
		this.maxHP = maxHP;
		hitbox = new AABB(0, 0, 0, 0);
	}
	@Deprecated
	public AABB getHitbox() {
		return hitbox;
	}
}
