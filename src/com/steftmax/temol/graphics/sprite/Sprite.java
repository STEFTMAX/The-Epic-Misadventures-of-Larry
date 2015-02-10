package com.steftmax.temol.graphics.sprite;

import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.resource.Disposable;

/**
 * TODO actually make this class useful for drawing. For example let it store
 * color and rotation, scaling, position. Upper layer of all images drawn to the
 * screen.
 * 
 * @author pieter3457
 *
 */
public class Sprite implements Disposable{

	public Vector2 pos;
	public int width, height;
	public float scaleX = 1, scaleY = 1;
	public boolean flipX = false, flipY = false;

	public TextureRegion texReg;
	public boolean containmentTest = true;

	public Sprite(Texture tex) {
		set(new TextureRegion(tex), new Vector2());
	}

	public Sprite(Texture tex, float x, float y) {
		set(new TextureRegion(tex), new Vector2(x, y));
	}

	public Sprite(TextureRegion texReg) {
		set(texReg, new Vector2());
	}

	public Sprite(TextureRegion texReg, float x, float y) {
		set(texReg, new Vector2(x, y));
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

	public void dispose() {

		texReg.dispose();
	}

	public void setScale(float scale) {
		this.scaleX = scale;
		this.scaleY = scale;
	}

	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public Texture getTexture() {
		return texReg.tex;
	}

	public int getVertexSize() {
		return 4;
	}

	public void set(Vector2 position) {
		this.pos.set(position);
	}

	public void lock(Vector2 position) {
		this.pos = position;
	}

	public void set(float x, float y) {
		pos.set(x, y);
	}

	public void setContainmentTest(boolean containmentTest) {
		this.containmentTest = containmentTest;
	}

	public boolean testContainment() {
		return containmentTest;
	}

	public float getScaledWidth() {
		return width * scaleX;
	}

	public float getScaledHeight() {
		return height * scaleY;
	}
}
