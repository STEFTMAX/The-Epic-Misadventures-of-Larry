package com.steftmax.larrys_epic_misadventures.sprite;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;

/**
 * This class loads SpriteSheets into Animations or Texture arrays.
 * 
 * @author pieter3457
 * 
 */
public final class SpriteSheet implements Loadable {

	private int rows, collumns;
	private String path;
	private TextureRegion[] regions;

	public SpriteSheet(String path, int rows, int collumns) {
		this.path = path;
		this.rows = rows;
		this.collumns = collumns;
	}

	public TextureRegion get(int index) {
		return regions[index];
	}

	// public static Sprite[] loadSprites(String path, int x, int y, int
	// skipLast) {
	//
	// final Texture spriteSheet = new Texture(path);
	//
	// spriteSheet.load();
	//
	// final int frameWidth = spriteSheet.getWidth() / x;
	// final int frameHeight = spriteSheet.getHeight() / y;
	//
	// // For debugging
	// if (x < 1 || y < 1 || frameHeight * y != spriteSheet.getHeight()
	// || frameWidth * x != spriteSheet.getWidth()) {
	// System.err.println("SpriteSheet ratio incorrect!");
	// }
	//
	// int counter = 0;
	// int max = x * y - skipLast;
	//
	// Sprite[] sprites = new Sprite[max];
	//
	// addFrames: for (int curY = 0; curY < y; curY++) {
	// for (int curX = 0; curX < x; curX++) {
	//
	// TextureRegion region = new TextureRegion(spriteSheet, curX
	// * frameWidth, curY * frameHeight, frameWidth,
	// frameHeight);
	// sprites[counter] = new Sprite(region);
	//
	// counter++;
	// if (counter == max) {
	// break addFrames;
	// }
	// }
	// }
	// return sprites;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#load()
	 */
	@Override
	public void load() {
		if (isLoaded())
			return;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#unload()
	 */
	@Override
	public void unload() {
		for (TextureRegion r : regions) {
			r.unload();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return regions != null;
	}

	public TextureRegion[] getFrames() {
		return regions;
	}
}
