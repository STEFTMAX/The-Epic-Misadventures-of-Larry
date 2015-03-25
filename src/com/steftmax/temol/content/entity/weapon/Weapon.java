package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.render.Updatable;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;

/**
 * @author pieter3457
 *
 */
public abstract class Weapon implements Drawable, Updatable{
	
	protected MouseInput mi;

	public Weapon(ResourceManager gameResources, MouseInput mi) {
		this.mi = mi;
	}
	
	public abstract boolean drawsFace();
}
