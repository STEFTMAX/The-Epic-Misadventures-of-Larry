package com.steftmax.temol.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;

import com.steftmax.temol.resource.Disposable;
import com.steftmax.temol.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class TextureAtlas implements Disposable {

	private int width, height, padding;
	private Node root;
	private boolean completed = false;
	private Texture theTexture;

	public TextureAtlas(int width, int height, int padding, int minfilter,
			int magfilter) {
		this.padding = padding;
		this.width = width;
		this.height = height;

		theTexture = new Texture(width, height, minfilter, magfilter);

		root = new Node(0, 0, width, height);
	}

	public TextureAtlas(int width, int height) {
		this(width, height, 1, GL11.GL_NEAREST, GL11.GL_NEAREST);
	}

	public TextureRegion add(String path) {

		BufferedImage img;
		try {
			img = ImageIO.read(ResourceLoader.load(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		final int width = img.getWidth();
		final int height = img.getHeight();

		Node tmp = root.insertChild(width, height);

		if (tmp == null) {
			return null;
		}

		final int x = tmp.x + padding;
		final int y = tmp.y + padding;
		
		ByteBuffer pixels = Texture.decodeImage(img, true);

		theTexture.bind();
		GL11.glTexSubImage2D(GL_TEXTURE_2D, 0, x, y, width, height,
				GL_RGBA, GL_UNSIGNED_BYTE, pixels);
		theTexture.unbind();

		Util.checkGLError();
		TextureRegion region = new TextureRegion(theTexture, x, y, width,
				height);
		return region;

	}

	/*
	 * Disposes the TextureAtlas. NOTE: This doesn't dispose the texture it
	 * holds.
	 */
	@Override
	public void dispose() {
		
	}

	public Texture getTexture() {
		return theTexture;
	}

	class Node {

		Node left, right;
		Texture tex;
		boolean inUse;
		int x, y, width, height;

		public Node(int x, int y, int width, int height) {
			// System.out.println("x: " + x);
			// System.out.println("y: " + y);
			// System.out.println("w: " + width);
			// System.out.println("h: " + height);
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public Node insertChild(int width, int height) {

			if (left != null) {
				Node tmp = left.insertChild(width, height);
				if (tmp == null) {
					tmp = right.insertChild(width, height);
				}
				return tmp;
			}

			final int textureWidth = width + 2 * padding;
			final int textureHeight = height + 2 * padding;

			if (inUse || textureWidth > this.width || textureHeight > this.height) {
				return null;
			}

			if (textureWidth == this.width && textureHeight == this.height) {
				inUse = true;
				return this;
			}

			if (this.width - textureWidth > this.height - textureHeight) {
				left = new Node(x, y, textureWidth, this.height);
				right = new Node(x + textureWidth, y, this.width - textureHeight,
						this.height);
			} else {
				left = new Node(x, y, this.width, textureHeight);
				right = new Node(x, y + textureHeight, this.width, this.height
						- textureHeight);
			}

			return left.insertChild(width, height);

		}
	}
}