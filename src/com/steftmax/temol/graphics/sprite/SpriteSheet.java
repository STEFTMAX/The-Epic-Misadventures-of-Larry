package com.steftmax.temol.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import javax.imageio.ImageIO;

import com.steftmax.temol.graphics.Texture;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.resource.loader.ResourceLoader;

/**
 * This class loads SpriteSheets into Animations or Texture arrays.
 * 
 * @author pieter3457
 * 
 */
public final class SpriteSheet {

	private TextureRegion[] regions;

	/**
	 * Creates a spritesheet and autosplits it based on bordercolor defined in
	 * the first pixel.
	 * 
	 * @param path
	 *            The path to load the sheet from.
	 * @throws IOException
	 */
	public SpriteSheet(String path) throws IOException {
		BufferedImage sheet = ImageIO.read(ResourceLoader.load(path));
		Texture sheetTexture = new Texture(sheet);

		final int width = sheet.getWidth();
		final int height = sheet.getHeight();

		int[] pixels = new int[width * height];

		sheet.getRGB(0, 0, width, height, pixels, 0, width);

		int pixel0 = pixels[0];

		BitSet edgeSet = new BitSet(width * height);
		BitSet alreadyContained = (BitSet) edgeSet.clone();

		for (int i = 0; i < pixels.length; i++) {
			edgeSet.set(i, pixels[i] == pixel0);
		}

		int index;
		ArrayList<TextureRegion> regions = new ArrayList<TextureRegion>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				index = y * width + x;

				if (!alreadyContained.get(index) && !edgeSet.get(index)) {
					int xlimit = width;
					int ylimit = height;

					limitSetting: for (int Y = y; Y < ylimit; Y++) {
						for (int X = x; X < xlimit; X++) {
							index = Y * width + X;
							if (edgeSet.get(index)) {
								if (X > x) {
									xlimit = X;
								} else {
									ylimit = Y;
									break limitSetting;
								}
							} else {

								alreadyContained.set(index);
							}
						}
					}
					xlimit -= x;
					ylimit -= y;
					// System.out.println("x: " + x);
					// System.out.println("y: " + y);
					// System.out.println("xlimit: " + xlimit);
					// System.out.println("ylimit: " + ylimit);

					regions.add(new TextureRegion(sheetTexture, x, y, xlimit,
							ylimit));
				}
			}
		}
		pixels = null;

		System.out.println("Amount of regions: " + regions.size());
		this.regions = regions.toArray(new TextureRegion[regions.size()]);
	}

	public SpriteSheet(String path, int rows, int collumns) {
		final Texture spriteSheet = new Texture(path);

		spriteSheet.load();

		final int frameWidth = spriteSheet.getWidth() / rows;
		final int frameHeight = spriteSheet.getHeight() / collumns;

		// For debugging
		if (rows < 1 || collumns < 1
				|| frameHeight * collumns != spriteSheet.getHeight()
				|| frameWidth * rows != spriteSheet.getWidth()) {
			System.err.println("SpriteSheet ratio incorrect!");
		}

		int counter = 0;
		int max = rows * collumns; // / - skipLast;

		regions = new TextureRegion[max];

		addFrames: for (int curY = 0; curY < collumns; curY++) {
			for (int curX = 0; curX < rows; curX++) {

				// System.out.println(curY * frameHeight);
				regions[counter] = new TextureRegion(spriteSheet, curX
						* frameWidth, curY * frameHeight, frameWidth,
						frameHeight);
				;

				counter++;
				if (counter == max) {
					break addFrames;
				}
			}
		}
	}

	public TextureRegion get(int index) {
		return regions[index];
	}

	public TextureRegion[] getFrames() {
		return regions;
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 *            To index exclusive.
	 * @return
	 */
	public TextureRegion[] obtainFrames(final int fromIndex, final int toIndex) {

		TextureRegion[] frames = new TextureRegion[toIndex - fromIndex];

		for (int i = fromIndex; i < toIndex; i++) {
			frames[i - fromIndex] = regions[i];
		}
		return frames;
	}
}
