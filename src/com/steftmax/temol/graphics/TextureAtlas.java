package com.steftmax.temol.graphics;

import com.steftmax.temol.resource.Disposable;

/**
 * @author pieter3457
 *
 */
public class TextureAtlas implements Disposable {

	private int width, height, padding;
	private FrameBuffer drawBuffer;
	private SpriteBatch drawer;
	private Node root;
	private boolean completed = false;

	public TextureAtlas(int width, int height, int padding) {
		this.padding = padding;
		this.width = width;
		this.height = height;

		root = new Node(0, 0, width, height);

		drawBuffer = new FrameBuffer(width, height);
		drawer = new SpriteBatch(1, width, height);
		drawer.begin();
	}

	public TextureAtlas(int width, int height) {
		this(width, height, 1);
	}

	public TextureRegion add(Texture texture) {

		Node tmp = root.insertChild(texture);

		if (tmp == null) {
			return null;
		}

		final int x = tmp.x + padding;
		final int y = tmp.y + padding;

		drawBuffer.begin();
		drawer.draw(texture, x, y);
		drawer.flush();
		drawBuffer.end();
		TextureRegion region = new TextureRegion(drawBuffer.getTexture(), x, y,
				width, height);
		return region;

	}

	/*
	 * Disposes the TextureAtlas. NOTE: This doesn't dispose the texture it
	 * holds.
	 */
	@Override
	public void dispose() {
		drawer.end();
	}

	public Texture getTexture() {
		return drawBuffer.getTexture();
	}
	
	class Node {

		Node left, right;
		Texture tex;
		boolean inUse;
		int x, y, width, height;

		public Node(int x, int y, int width, int height) {
			System.out.println("x: " + x);
			System.out.println("y: " + y);
			System.out.println("w: " + width);
			System.out.println("h: " + height);
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public Node insertChild(Texture texture) {

			if (left != null) {
				Node tmp = left.insertChild(texture);
				if (tmp == null) {
					tmp = right.insertChild(texture);
				}
				return tmp;
			}

			final int textureWidth = texture.width + 2 * padding;
			final int textureHeight = texture.height + 2 * padding;

			if (inUse || textureWidth > width || textureHeight > height) {
				return null;
			}

			if (textureWidth == width && textureHeight == height) {
				inUse = true;
				return this;
			}

			if (width - textureWidth > height - textureHeight) {
				left = new Node(x, y, textureWidth, height);
				right = new Node(x + textureWidth, y, width - textureHeight,
						height);
			} else {
				left = new Node(x, y, width, textureHeight);
				right = new Node(x, y + textureHeight, width, height
						- textureHeight);
			}

			return left.insertChild(texture);

		}
	}
}