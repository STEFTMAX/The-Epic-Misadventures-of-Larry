package com.steftmax.larrys_epic_misadventures.sprite;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;

/**
 * @author pieter3457
 *
 */
public class TextureRegion implements Loadable {

	private final Texture tex;
	private final int x, y;
	public final int width, height;

	public TextureRegion(Texture t) {
		this(t, 0, 0, t.width, t.height);
		// System.out.println("CALLED!");
	}

	public TextureRegion(Texture texture, int x, int y, int width, int height) {
		this.tex = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Deprecated // TODO the spritebatch should draw it all nubz
	public void draw(float drawX, float drawY) {
		tex.bind();

		final float leftX =  (x / (float) tex.width);
		final float rightX =  ((x + width) / (float) tex.width);
		final float topY =  (y / (float) tex.height);
		final float bottomY = ((height + y) / (float) tex.height);

//		System.out.println("leftX : " + leftX);
//		System.out.println("rightX : " + rightX);
//		System.out.println("topY : " + topY);
//		System.out.println("bottomY : " + bottomY);

		glBegin(GL_TRIANGLES);

		glTexCoord2f(rightX, topY);// Right top
		glVertex2f(drawX + width, drawY);
		glTexCoord2f(leftX, topY);// Left top
		glVertex2f(drawX, drawY);
		glTexCoord2f(leftX, bottomY);// Left bottem
		glVertex2f(drawX, drawY + height);

		glTexCoord2f(leftX, bottomY); // Left bottom
		glVertex2f(drawX, drawY + height);
		glTexCoord2f(rightX, bottomY); // Right bottom
		glVertex2f(drawX + width, drawY + height);
		glTexCoord2f(rightX, topY); // Right top
		glVertex2f(drawX + width, drawY);

		glEnd();

		tex.unbind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#load()
	 */
	@Override
	public void load() {
		tex.load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#unload()
	 */
	@Override
	public void unload() {
		tex.unload();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return tex.isLoaded();
	}
}
