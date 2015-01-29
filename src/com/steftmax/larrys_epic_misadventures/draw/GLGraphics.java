package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.*;

import com.steftmax.larrys_epic_misadventures.math.Vector2;
import com.steftmax.larrys_epic_misadventures.sprite.TextureRegion;

/**
 * @author pieter3457 Class that simplifies drawing in opengl
 */
public class GLGraphics {

	public static void drawFromLeftBottomFlipped(TextureRegion tex,
			Vector2 newPos) {
		drawFlipped(tex, newPos.x, newPos.y - tex.height);
	}

	public static void drawFromLeftBottom(TextureRegion tex, Vector2 p) {

		draw(tex, p.x, p.y - tex.height);
	}

	public static void draw(TextureRegion tex, Vector2 pos) {
		draw(tex, pos.x, pos.y);
	}

	public static void drawFlipped(TextureRegion tex, Vector2 pos) {
		drawFlipped(tex, pos.x, pos.y);
	}

	public static void draw(TextureRegion tex, float x, float y) {

		int height = tex.height;
		int width = tex.width;

		glBegin(GL_TRIANGLES);

		glTexCoord2f(1, 0);// Right top
		glVertex2f(x + width, y);
		glTexCoord2f(0, 0);// Left top
		glVertex2f(x, y);
		glTexCoord2f(0, 1);// Left bottem
		glVertex2f(x, y + height);

		glTexCoord2f(0, 1); // Left bottom
		glVertex2f(x, y + height);
		glTexCoord2f(1, 1); // Right bottom
		glVertex2f(x + width, y + height);
		glTexCoord2f(1, 0); // Right top
		glVertex2f(x + width, y);

		glEnd();

	}

	public static void drawFlipped(TextureRegion tex, float x, float y) {

		int height = tex.height;
		int width = tex.width;

		glBegin(GL_TRIANGLES);

		glTexCoord2f(1, 0);// Right top
		glVertex2f(x, y);
		glTexCoord2f(0, 0);// Left top
		glVertex2f(x + width, y);
		glTexCoord2f(0, 1);// Left bottem
		glVertex2f(x + width, y + height);

		glTexCoord2f(0, 1); // Left bottom
		glVertex2f(x + width, y + height);
		glTexCoord2f(1, 1); // Right bottom
		glVertex2f(x, y + height);
		glTexCoord2f(1, 0); // Right top
		glVertex2f(x, y);
		glEnd();
	}
	
	public static void drawScaled(TextureRegion tex, float x, float y,
			float scale) {
		glPushMatrix();
		glTranslatef(-x, -y, 1f);
		glScalef(scale, scale, 1);
		
		draw(tex, x, y);
		
		glPopMatrix();
	}
}
