package com.steftmax.temol.graphics.sprite;

import com.steftmax.temol.resource.Disposable;

/**
 * This class loads SpriteSheets into Animations or Texture arrays.
 * 
 * @author pieter3457
 * 
 */
public final class SpriteSheet implements Disposable {

	private int rows, collumns;
	private TextureRegion[] regions;

	public SpriteSheet(String path, int rows, int collumns) {
		this.rows = rows;
		this.collumns = collumns;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		for (TextureRegion r : regions) {
			r.dispose();
		}
	}

	public TextureRegion[] getFrames() {
		return regions;
	}
}
