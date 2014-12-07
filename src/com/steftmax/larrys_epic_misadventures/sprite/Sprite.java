package com.steftmax.larrys_epic_misadventures.sprite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;
import com.steftmax.larrys_epic_misadventures.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class Sprite implements Loadable {

	private final String path;
	private BufferedImage sprite;

	public Sprite(String path) {
		this.path = path;
	}
	
	public BufferedImage getImage() {
		return sprite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */
	@Override
	public void load() {
		if (path == null) return;
		try {
			sprite = ImageIO.read(ResourceLoader.load(path));
		} catch (Exception e) {

			// Creates a missing texture image
			sprite = new BufferedImage(32, 32, 2);
			Graphics g = sprite.getGraphics();
			g.setColor(Color.red);
			g.drawString("wrong", 0, 10);
			g.drawString("path", 0, 30);
			g.dispose();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		this.sprite = null;
	}
	
	/**
	 * This method decodes a bufferedImage
	 * 
	 * @param texture
	 *            The image from which the ByteBuffer is created
	 * @param alpha
	 *            Whether or not to use alpha in this ByteBuffer
	 * @return A ByteBuffer with all the pixels in it
	 */
	public static ByteBuffer decodePNG(BufferedImage texture, boolean alpha) {

		ByteBuffer buffer = BufferUtils.createByteBuffer(texture.getWidth()
				* texture.getHeight() * (alpha ? 4 : 3));

		int[] pixels = new int[texture.getWidth() * texture.getHeight()];

		texture.getRGB(0, 0, texture.getWidth(), texture.getHeight(),
				pixels, 0, texture.getWidth());

		for (int y = 0; y < texture.getHeight(); y++) {
			for (int x = 0; x < texture.getWidth(); x++) {
				int pixel = pixels[y * texture.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				if (alpha)
					buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		buffer.flip();
		return buffer;
	}
}
