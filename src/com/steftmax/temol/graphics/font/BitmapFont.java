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

	public BitmapFont(String letters, TextureRegion[] glyphs, Color color) {
		this(letters, glyphs);
		this.color = color;
	}

	public BitmapFont(String letters, TextureRegion[] glyphs) {
		letters = letters.toLowerCase();
		if (!letters.contains(" ")) {
			letters = letters + " ";
		}
		
		this.letters = letters;

		this.glyphs = new TextureRegion[letters.length() + 1];
		for (int i = 0; i < this.glyphs.length; i++) {
			this.glyphs[i] = glyphs[i];
		}
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			float scaleX, float scaleY) {
		draw(batch, text, x, y, scaleX, scaleY, this.color);
	}

	public void draw(SpriteBatch batch, String text, float x, float y,
			float scaleX, float scaleY, Color c) {
		
		float px;
		for (int i = 0; i < text.length(); i++) {
			TextureRegion glyph = glyphs[letters.indexOf(Character.toLowerCase(text.charAt(i)))];
			px = x + i * glyph.width;

			batch.draw(glyph, px, px + glyph.width * scaleX, y, y
					+ glyph.height * scaleY, false, false, false, c);
		}
	}
}
