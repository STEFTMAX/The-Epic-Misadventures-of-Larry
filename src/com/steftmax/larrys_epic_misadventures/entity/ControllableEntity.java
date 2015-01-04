/**
 * @author pieter3457
 *
 */
package com.steftmax.larrys_epic_misadventures.entity;

import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;

public abstract class ControllableEntity extends Entity {

	protected final KeyboardInput ki;
	protected final MouseInput mi;

	public ControllableEntity(TiledMap map, float x, float y, final int mass, final int maxHP, KeyboardInput ki, MouseInput mi) {
		super(map, x, y, mass, maxHP);
		this.ki = ki;
		this.mi = mi;
	}

}