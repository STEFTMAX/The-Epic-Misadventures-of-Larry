package com.steftmax.temol.graphics.font;

import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.TextureRegion;

/**
 * @author pieter3457
 *
 */
public class BitmapFont {
	final String letters;
	final TextureRegion[] glyphs;

	public BitmapFont(String letters, TextureRegion[] glyphs) {
		this.letters = letters;

		this.glyphs = new TextureRegion[letters.length()];
		for (int i = 0; i < this.glyphs.length; i++) {
			this.glyphs[i] = glyphs[i];
		}
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			float scaleX, float scaleY) {
		for (int i = 0; i < text.length(); i++) {
			TextureRegion glyph = glyphs[letters.indexOf(text.charAt(i))];
			batch.draw(glyph, x * i, x * i + glyph.width * scaleX, y, y
					+ glyph.height * scaleY, false, false, false);
		}
	}
}
