package com.steftmax.larrys_epic_misadventures.resource.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.sprite.animation.PlaySequence;

/**
 * This class loads SpriteSheets into Animations or Texture arrays.
 * @author pieter3457
 * 
 */
public final class SpriteSheetLoader{

	private SpriteSheetLoader(){}
	
	public static Texture[] loadTextures(String path, int x, int y, int skipLast) {
		
		BufferedImage spriteSheet;
		try {
			spriteSheet = ImageIO.read(ResourceLoader.load(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		int frameWidth = spriteSheet.getWidth() / x;
		int frameHeight = spriteSheet.getHeight() / y;

		// For debugging
		if (x < 1 || y < 1 || frameHeight * y != spriteSheet.getHeight()
				|| frameWidth * x != spriteSheet.getWidth()) {
			System.err.println("SpriteSheet ratio incorrect!");
		}

		int counter = 0;
		int max = x * y - skipLast;

		Texture[] sprites = new Texture[max];

		addFrames: for (int curY = 0; curY < y; curY++) {
			for (int curX = 0; curX < x; curX++) {

				sprites[counter] = new Texture(spriteSheet.getSubimage(curX * frameWidth,
						curY * frameHeight, frameWidth, frameHeight));
				sprites[counter].load();

				counter++;
				if (counter == max) {
					break addFrames;
				}
			}
		}
		return sprites;
	}
	
	@Deprecated
	public static Animation loadAnimation(String path, int x, int y, int skipLast, PlaySequence sequence, int fps) {
		return new Animation(loadTextures(path, x, y, skipLast), sequence, fps);
	}
}
