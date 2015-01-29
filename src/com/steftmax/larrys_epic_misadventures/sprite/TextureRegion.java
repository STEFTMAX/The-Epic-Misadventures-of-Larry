package com.steftmax.larrys_epic_misadventures.sprite;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;

/**
 * @author pieter3457
 *
 */
public class TextureRegion implements Loadable {

	public final Texture tex;
	public final int x, y, width, height;
	public float left, right, top, bottom;

	public TextureRegion(Texture t) {
		this(t, 0, 0, t.width, t.height);
		// System.out.println("CALLED!");
	}

	public TextureRegion(Texture texture, int x, int y, int width, int height) {
		this.tex = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		left =  (x / (float) tex.width);
		right =  ((x + width) / (float) tex.width);
		top = (y / (float) tex.height);
		bottom = ((height + y) / (float) tex.height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#load()
	 */
	@Override
	public void load() {
		tex.load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#unload()
	 */
	@Override
	public void unload() {
		tex.unload();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return tex.isLoaded();
	}
}
