package com.steftmax.temol.graphics.sprite;

import com.steftmax.temol.resource.Disposable;

/**
 * @author pieter3457
 *
 */
public class TextureRegion implements Disposable {

	public final Texture texture;
	public final int x, y, width, height;
	public float u1, u2, v1, v2;
	private final static float BLEED = 0.000015f;

	public TextureRegion(Texture t) {
		this(t, 0, 0, t.width, t.height);
	}

	public TextureRegion(Texture texture, int x, int y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		// this way because texturecoordinates are y flipped
		u1 = (x / (float) texture.width);
		u2 = ((x + width) / (float) texture.width);
		v1 = ((y + height) / (float) texture.height);
		v2 = (y / (float) texture.height);

		// Antibordering #2
		bleed();

		// System.out.println(texture);
		// System.out.println(left);
		// System.out.println(right);
		// System.out.println(top);
		// System.out.println(bottom);
		// System.out.println(x);
		// System.out.println(y);
		// System.out.println(width);
		// System.out.println(height);
		//

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#dispose()
	 */
	@Override
	public void dispose() {
		texture.dispose();
	}
}
