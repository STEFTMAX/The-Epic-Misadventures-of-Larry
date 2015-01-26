package com.steftmax.larrys_epic_misadventures.resource.loader;

import java.util.HashMap;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 *
 */
public abstract class ResourceManager implements Loadable{
	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public ResourceManager() {
		
	}
	@Override
	public abstract void load();
	
	@Override
	public void unload() {
		//Textures
		for (Texture t : textures.values()) {
			t.unload();
		}
		textures.clear();
		
	}
	
	
}
