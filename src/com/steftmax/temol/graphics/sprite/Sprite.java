package com.steftmax.temol.graphics.sprite;

import com.steftmax.temol.graphics.Color;
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
public class Sprite implements Disposable {

	public Vector2 pos;
	public int width, height;
	public float scaleX = 1, scaleY = 1;
	public boolean flipX = false, flipY = false;

	public float rotation = 0f; // in radians
	public final Vector2 origin = new Vector2();

	public TextureRegion texReg;
	public Color color = new Color(127, 127, 127, 127);
	
	public void setTextureRegion(TextureRegion textureRegion) {
		this.texReg = textureRegion;
	}
	
	public void setToTextureRegion(TextureRegion textureRegion) {
		this.texReg = textureRegion;
		this.width = textureRegion.width;
		this.height = textureRegion.height;
	}

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
		this.pos = new Vector2();
	}
	
	public Sprite(Vector2 position) {
		this.pos = position;
	}

	public void set(TextureRegion region, Vector2 pos) {
		this.texReg = region;
		this.width = region.width;
		this.height = region.height;
		centerOrigin();
		this.pos = pos;
	}

	public void set(TextureRegion region, float x, float y) {
		this.texReg = region;
		this.width = region.width;
		this.height = region.height;
		centerOrigin();
		pos.set(x, y);
	}

	public void centerOrigin() {
		origin.set(width / 2, height / 2);
	}

	public void setOrigin(Vector2 v) {
		origin.set(v);
	}

	public void setOrigin(float x, float y) {
		origin.set(x, y);
	}

	public void dispose() {

		texReg.dispose();
	}

	public void setRotation(float radians) {
		this.rotation = radians;
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
		return texReg.texture;
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

	public float getScaledWidth() {
		return width * scaleX;
	}

	public float getScaledHeight() {
		return height * scaleY;
	}

	/**
	 * @param width
	 * @param height
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;

	}

	public void rotate(float radians) {
		rotation += radians;
	}
}
