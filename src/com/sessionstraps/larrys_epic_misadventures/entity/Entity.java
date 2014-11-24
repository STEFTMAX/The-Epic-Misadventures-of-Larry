package com.sessionstraps.larrys_epic_misadventures.entity;

import static org.lwjgl.opengl.GL11.*;

import com.sessionstraps.game_engine.level.LevelObject;
import com.sessionstraps.game_engine.physics.Position;
import com.sessionstraps.game_engine.render.Drawable;
import com.sessionstraps.game_engine.render.Updatable;
import com.sessionstraps.game_engine.resources.Texture;

public abstract class Entity extends LevelObject implements Drawable, Updatable {

	protected final Position pos;
	protected float HP;
	protected Texture drawingTexture;

	public Entity(float x, float y) {
		pos = new Position(x, y);
	}

	/**
	 * Draws an image to the specified image, it can be swapped with the boolean
	 * argument.
	 * 
	 * @param g
	 *            The graphics to draw to
	 * @param swapLeftRight
	 *            Whether or not to swap the image
	 */
	// TODO move to imageDrawer Class
	protected void drawImage(boolean swapLeftRight) {

		drawingTexture.bind();

		float x = pos.getAbsoluteX();
		float y = pos.getAbsoluteY();
		int height = drawingTexture.getHeight();
		int halfwidth = drawingTexture.getWidth() / 2;

		glBegin(GL_TRIANGLES);

		if (swapLeftRight) {

			// inverted Drawing

			glTexCoord2f(1, 0);// Right top
			glVertex2f(x - halfwidth, y - height);
			glTexCoord2f(0, 0);// Left top
			glVertex2f(x + halfwidth, y - height);
			glTexCoord2f(0, 1);// Left bottem
			glVertex2f(x + halfwidth, y);

			glTexCoord2f(0, 1); // Left bottom
			glVertex2f(x + halfwidth, y);
			glTexCoord2f(1, 1); // Right bottom
			glVertex2f(x - halfwidth, y);
			glTexCoord2f(1, 0); // Right top
			glVertex2f(x - halfwidth, y - height);

		} else {

			glTexCoord2f(1, 0);// Right top
			glVertex2f(x + halfwidth, y - height);
			glTexCoord2f(0, 0);// Left top
			glVertex2f(x - halfwidth, y - height);
			glTexCoord2f(0, 1);// Left bottem
			glVertex2f(x - halfwidth, y);

			glTexCoord2f(0, 1); // Left bottom
			glVertex2f(x - halfwidth, y);
			glTexCoord2f(1, 1); // Right bottom
			glVertex2f(x + halfwidth, y);
			glTexCoord2f(1, 0); // Right top
			glVertex2f(x + halfwidth, y - height);

		}

		glEnd();

		drawingTexture.unbind();

	}

}
