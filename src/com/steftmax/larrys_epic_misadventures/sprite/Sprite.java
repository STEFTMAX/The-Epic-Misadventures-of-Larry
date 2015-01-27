package com.steftmax.larrys_epic_misadventures.sprite;

import com.steftmax.larrys_epic_misadventures.math.Vector2;

/**
 * TODO actually make this class useful for drawing. For example let it store
 * color and rotation, scaling, position. Upper layer of all images drawn to the
 * screen.
 * 
 * @author pieter3457
 *
 */
public class Sprite {

	public Vector2 pos;
	public int width, height;
	public boolean flipX = false, flipY = false;
	private TextureRegion texReg;

	public Sprite(Texture tex) {
		set(new TextureRegion(tex), new Vector2());
	}

	public Sprite(TextureRegion texReg) {
		set(texReg, new Vector2());
	}

	public Sprite() {
	}

	public void set(TextureRegion region, Vector2 pos) {
		this.texReg = region;
		this.width = region.width;
		this.height = region.height;
		this.pos = pos;
	}

	public void set(TextureRegion region, float x, float y) {
		this.texReg = region;
		this.width = region.width;
		this.height = region.height;
		pos.set(x, y);
	}

	public void unload() {

		texReg.unload();
	}

	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void draw(float x, float y) {
		texReg.draw(x, y);
	}

}
