package com.steftmax.larrys_epic_misadventures.resource;

/**
 * A Loadable is an object such as a texture or animation that can be loaded and
 * may use a similarly named Loader to load its resources.
 * 
 * @author pieter3457
 *
 */
public interface Loadable {

	/**
	 * Loads the object into the ram or vram depending on the object.
	 */
	public void load();

	/**
	 * Unloads the object.
	 */
	public void unload();
	
	
	
	/**
	 * Determines whether or not the object is loaded.
	 * @return True if the object is loaded.
	 */
	public boolean isLoaded();

}
