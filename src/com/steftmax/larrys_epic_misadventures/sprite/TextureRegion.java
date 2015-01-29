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
	private final static float BLEED = 0.000015f;

	public TextureRegion(Texture t) {
		this(t, 0, 0, t.width, t.height);
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
		bottom = ((y + height) / (float) tex.height);
		

//		Antibordering #2
		bleed();
		
//		System.out.println(texture);
//		System.out.println(left);
//		System.out.println(right);
//		System.out.println(top);
//		System.out.println(bottom);
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(width);
//		System.out.println(height);
//		
		
	}

	/**
	 * 
	 */
	private void bleed() {
		left +=BLEED;
		right -=BLEED;
		top +=BLEED;
		bottom -=BLEED;
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
