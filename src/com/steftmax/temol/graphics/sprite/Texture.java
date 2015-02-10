package com.steftmax.temol.graphics.sprite;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import com.steftmax.temol.resource.Disposable;
import com.steftmax.temol.resource.loader.ResourceLoader;

/**
 * 
 * Under layer of all images drawn on the screen.
 * 
 * @author pieter3457
 *
 */
public class Texture implements Disposable {

	private int id;
	public int width, height;

	public int getID() {
		return id;
	}
	
	/**
	 * @return the width of this texture
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height of this texture
	 */
	public int getHeight() {
		return height;
	}

	public Texture(String path) {

		final InputStream is = ResourceLoader.load(path);
		BufferedImage img;

		try {
			img = ImageIO.read(is);
		} catch (Exception e) {

			// Creates a missing texture image
			img = new BufferedImage(32, 32, 2);
			Graphics g = img.getGraphics();
			g.setColor(Color.red);
			g.drawString("wrong", 0, 10);
			g.drawString("path", 0, 30);
			g.dispose();

		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		initTexture(img.getWidth(), img.getHeight(), decodePNG(img, true));
	}

	/**
	 * @param width
	 * @param height
	 */
	public Texture(int width, int height) {
		initTexture(width, height, null);
	}

	private void initTexture(int width, int height, ByteBuffer data) {
		this.height = height;
		this.width = width;

		id = glGenTextures();

		glBindTexture(GL_TEXTURE_2D, id);
		// Set the parameters
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		// Anti bordering #1

		// Loads the texture
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA,
				GL_UNSIGNED_BYTE, data);

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	@Override
	public void dispose() {
		unbind();
		glDeleteTextures(id);
	}

	public void load() {

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

		texture.getRGB(0, 0, texture.getWidth(), texture.getHeight(), pixels,
				0, texture.getWidth());

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
