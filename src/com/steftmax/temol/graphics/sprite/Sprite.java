package com.steftmax.temol.graphics.sprite;

import com.steftmax.temol.graphics.Color;
import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.Texture;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.math.Vector2;

/**
 * TODO actually make this class useful for drawing. For example let it store
 * color and rotation, scaling, position. Upper layer of all images drawn to the
 * screen.
 * 
 * @author pieter3457
 *
 */
public class Sprite extends TextureRegion implements Drawable {

	public Vector2 pos = new Vector2();
	public float scaleX = 1, scaleY = 1;
	public boolean flipX = false, flipY = false;

	public float rotation = 0f; // in radians
	public final Vector2 origin = new Vector2();
	boolean originCentered = true;

	public Color color = new Color(127, 127, 127, 127);
	public float width, height;

	public void setToTextureRegion(TextureRegion textureRegion) {
		super.setTo(textureRegion);
	}

	public Sprite(Texture tex) {
		super(tex);
		init();
	}

	public Sprite(Texture tex, float x, float y) {
		super(tex);
		pos.set(x, y);
		init();
	}

	public Sprite(TextureRegion texReg) {
		super(texReg);
		init();
	}

	public Sprite(TextureRegion texReg, float x, float y) {
		super(texReg);
		pos.set(x, y);
		init();
	}

	private void init() {
		
		this.width = regionWidth;
		this.height = regionHeight;

		centerOrigin();

	}

	public void centerOrigin() {
		origin.set(regionWidth * scaleX * .5f, regionHeight * scaleY * .5f);
		originCentered = true;
	}

	public void setOrigin(Vector2 v) {
		origin.set(v);
		originCentered = false;
	}

	public void setOrigin(float x, float y) {
		origin.set(x, y);
		originCentered = false;
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

	public int getVertexSize() {
		return 4;
	}

	public void setPosition(Vector2 position) {
		this.pos = position;
	}

	public void setPosition(float x, float y) {
		pos.set(x, y);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	/**
	 * Sets the dimensions of this sprite. It doesn't affect the scale.
	 * @param width The height.
	 * @param height The width.
	 */
	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
	}

	public void rotate(float radians) {
		rotation += radians;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics
	 * .SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(this, pos.x, pos.y, width, height, scaleX, scaleY, flipX,
				flipY, color, rotation, origin.x, origin.y);
	}
}
