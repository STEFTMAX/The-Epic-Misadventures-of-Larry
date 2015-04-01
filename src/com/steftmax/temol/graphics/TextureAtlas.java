package com.steftmax.temol.graphics;

import java.util.ArrayList;

import com.steftmax.temol.math.AABB;
import com.steftmax.temol.resource.Disposable;

/**
 * @author pieter3457
 *
 */
public class TextureAtlas implements Disposable{

	private int width, height, padding = 1;
	private ArrayList<TextureRegion> regions = new ArrayList<>();
	private FrameBuffer drawBuffer;
	private Node firstNode;
	private boolean completed = false;

	public TextureAtlas(int width, int height, int padding) {
		this(width, height);
		this.padding = padding;
	}

	public TextureAtlas(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public boolean add(TextureRegion region) {
		if (region.width > width || region.height > height)
			return false;

		if (regions.isEmpty()) {

			regions.add(region);

		} else {
			for (int i = 0; i < regions.size(); i++) {
				TextureRegion current = regions.get(i);

				if (current.width < region.width
						&& current.height < region.height) {
					regions.add(i, region);
				}
			}
		}
		return true;
	}

	public ArrayList<TextureRegion> pack() {
		for (TextureRegion region : regions) {

		}

		completed = true;
		return null;

	}

	public Texture getTexture() {
		if (completed ) {
			return drawBuffer.getTexture();
		} else {
			pack();
			return drawBuffer.getTexture();
		}
	}

	/* 
	 * Disposes the TextureAtlas. NOTE: This doesn't dispose the texture it holds.
	 */
	@Override
	public void dispose() {
		
	}
}

class Node {
	Node[] childs;
	TextureRegion region;
	AABB aabb;

	private boolean isLeaf() {
		return childs == null;
	}

	public Node insert(TextureRegion region) {
		if( !isLeaf()) {
			newNode = childs[0].ins
		}
	}
}