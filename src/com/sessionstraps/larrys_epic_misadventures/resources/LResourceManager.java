package com.sessionstraps.larrys_epic_misadventures.resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sessionstraps.game_engine.level.Level;
import com.sessionstraps.game_engine.resources.ResourceManager;
import com.sessionstraps.game_engine.sprite.animation.Animation;

/**
 * ResourceManager for the game Larry's Epic Misadventures. Stores all images in
 * a bufferedimage[] set.
 * 
 * @author pieter3457
 */
public class LResourceManager implements ResourceManager {

	// animations are stored in this map, stored <"filename.extension",
	// Animation>
	public HashMap<String, Animation> animations = new HashMap<String, Animation>();

	// same as above, only this stores the images, aka sprites
	public HashMap<String, BufferedImage[]> sprites = new HashMap<String, BufferedImage[]>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.resources.ResourceManager#loadMenuResources
	 * ()
	 */
	@Override
	public void loadMenuResources() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.resources.ResourceManager#loadGameResources
	 * ()
	 */
	@Override
	public void loadLevelResources(Level level) {
		for (String s : level.getNeededResources()) {
			// TODO create gameresource loader
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.resources.ResourceManager#unloadMenuResources
	 * ()
	 */
	@Override
	public void unloadMenuResources() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.resources.ResourceManager#unloadGameResources
	 * ()
	 */
	@Override
	public void unloadLevelResources() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.ResourceManager#unloadAll()
	 */
	@Override
	public void unloadAll() {
		// TODO Auto-generated method stub

	}

}
