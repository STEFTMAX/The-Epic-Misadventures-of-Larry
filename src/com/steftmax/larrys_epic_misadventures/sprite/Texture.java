package com.steftmax.larrys_epic_misadventures.sprite;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL12;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;

/**
 * @author pieter3457
 *
 */
public class Texture implements Loadable{

	private int id;
	public int width, height;
	private Sprite sprite;
	private BufferedImage image;

	
	public Texture(Sprite s) {
		this.sprite = s;
	}

	/**
	 * @param image
	 */
	public Texture(BufferedImage image) {
		this.image = image;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	@Override
	public void unload() {
		glDeleteTextures(id);
	}


	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */
	@Override
	public void load() {
		if (sprite != null) {
			sprite.load();
			image = sprite.getImage();
			sprite = null;
		}
		
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		// Set the parameters
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);// This is for pixelated look :D. Might be using mipmaps in the future of the spirtebach
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);//ANTI BORDERING :D
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		this.height = image.getHeight();
		this.width = image.getWidth();

		// Loads the texture
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(),
				image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				Sprite.decodePNG(image, true));
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
}
