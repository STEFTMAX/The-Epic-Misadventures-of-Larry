package com.steftmax.larrys_epic_misadventures.sprite;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;

/**
 * 
 * Upper layer of all images drawn to the screen.
 * @author pieter3457
 *
 */
public class Sprite implements Loadable {
	public int width, height;
	private final Texture tex;

	public Sprite(Texture tex) {
		this.tex = tex;
		if (tex.isLoaded()) {
			this.width = tex.getWidth();
			this.height = tex.getHeight();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */
	@Override
	public void load() {
		
		tex.load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		
		tex.unload();;
	}
	
	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return tex.isLoaded();
	}

}
