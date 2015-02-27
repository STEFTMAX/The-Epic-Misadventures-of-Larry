package com.steftmax.temol.graphics.font;

import com.steftmax.temol.graphics.Color;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.TextureRegion;

/**
 * @author pieter3457
 *
 */
public class BitmapFont {
	final String letters;
	final TextureRegion[] glyphs;
	private Color color;
	private int glyphWidth, glyphHeight;

	public BitmapFont(String letters, TextureRegion[] glyphs) {
		this(letters, glyphs, new Color(1, 1, 1, 1));
	}

	public BitmapFont(String letters, TextureRegion[] glyphs, Color color) {
		letters = letters.toLowerCase();
		if (!letters.contains(" ")) {
			letters = letters + " ";
		}

		this.letters = letters;

		this.glyphs = new TextureRegion[letters.length() + 1];
		for (int i = 0; i < this.glyphs.length; i++) {
			this.glyphs[i] = glyphs[i];
		}
		// cuz monospace u know
		this.glyphWidth = glyphs[0].width;
		this.glyphHeight = glyphs[0].height;

		this.color = color;
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			float scaleX, float scaleY) {
		draw(batch, text, x, y, scaleX, scaleY, this.color);
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			int pixWidth, int pixHeight, boolean keepRatio) {
		draw(batch, text, x, y, pixWidth, pixHeight, this.color, keepRatio);
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			int pixWidth, int pixHeight, Color color, boolean keepRatio) {

		float scaleX = (float) pixWidth / (text.length() * glyphWidth);
		float scaleY = (float) pixHeight / (glyphHeight);

		if (keepRatio) {
			float finalScale = Math.min(scaleX, scaleY);
			scaleX = finalScale;
			scaleY = finalScale;
		}

		draw(batch, text, x, y, scaleX, scaleY, color);
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			float scaleX, float scaleY, Color c) {

		float px;
		for (int i = 0; i < text.length(); i++) {
			TextureRegion glyph = glyphs[letters.indexOf(Character
					.toLowerCase(text.charAt(i)))];
			px = x + i * glyph.width * scaleX;

			batch.draw(glyph, px, px + glyph.width * scaleX, y, y
					+ glyph.height * scaleY, false, false, false, c);
		}
	}
}