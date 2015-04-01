package com.steftmax.temol.graphics;

/**
 * @author pieter3457
 *
 */
public class TextureRegion {

	public Texture texture;
	public int regionWidth, regionHeight;
	public float u1, u2, v1, v2;
	private final static float BLEED = 0.000015f;

	public TextureRegion(Texture t) {
		this(t, 0, 0, t.width, t.height);
	}

	public TextureRegion(Texture texture, int x, int y, int width, int height) {
		this(texture, x, y, width, height, true);
	}

	public TextureRegion(Texture texture, int x, int y, int width, int height,
			boolean bleed) {
		this.texture = texture;
		this.regionWidth = width;
		this.regionHeight = height;

		// this way because texturecoordinates are y flipped
		u1 = (x / (float) texture.width);
		u2 = ((x + width) / (float) texture.width);
		v1 = ((y + height) / (float) texture.height);
		v2 = (y / (float) texture.height);

		// Antibordering #2
		if (bleed)
			bleed();
	}

	/**
	 * Copies the specified textureRegion
	 * 
	 * @param texReg
	 */
	public TextureRegion(TextureRegion textureRegion) {
		setTo(textureRegion);
	}

	/**
	 * 
	 */
	private void bleed() {
		u1 += BLEED;
		u2 -= BLEED;
		v2 += BLEED;
		v1 -= BLEED;
	}

	/**
	 * @param textureRegion
	 */
	public void setTo(TextureRegion textureRegion) {
		this.texture = textureRegion.texture;

		this.regionWidth = textureRegion.regionWidth;
		this.regionHeight = textureRegion.regionHeight;

		this.u1 = textureRegion.u1;
		this.u2 = textureRegion.u2;
		this.v1 = textureRegion.v1;
		this.v2 = textureRegion.v2;
	}
	
	public Texture getTexture() {
		return texture;
	}

}
