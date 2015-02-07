/**
 * @author pieter3457
 *
 */
package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;

public abstract class ControllableEntity extends Entity {

	protected final KeyboardInput ki;
	protected final MouseInput mi;

	public ControllableEntity(TiledMap map, float x, float y, final int mass, final int maxHP, KeyboardInput ki, MouseInput mi) {
		super(map, x, y, mass, maxHP);
		this.ki = ki;
		this.mi = mi;
	}

}