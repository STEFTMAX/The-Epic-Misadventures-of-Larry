package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.steftmax.larrys_epic_misadventures.physics.Vector2F;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 * Class that simplifies drawing in opengl
 */
public class GLGraphics {

	public static void drawTextureFromLeftBottomFlipped(Texture tex, Vector2F newPos) {
		drawTextureFlipped(tex, newPos.x, newPos.y - tex.height);
	}
	
	public static void drawTextureFromLeftBottom(Texture tex, Vector2F p) {
		
		drawTexture(tex, p.x, p.y - tex.height);
	}
	
	public static void drawTexture(Texture tex, Vector2F pos) {
		drawTexture(tex, pos.x, pos.y);
	}
	
	public static void drawTextureFlipped(Texture tex, Vector2F pos) {
		drawTextureFlipped(tex, pos.x, pos.y);
	}
	
	
	public static void drawTexture(Texture tex, float x, float y) {

		tex.bind();
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
		
		tex.unbind();
	}

	public static void drawTextureFlipped(Texture tex, float x, float y) {

		tex.bind();
		
		int height = tex.height;
		int width = tex.width;

		glBegin(GL_TRIANGLES);

		glTexCoord2f(1, 0);// Right top
		glVertex2f(x, y);
		glTexCoord2f(0, 0);// Left top
		glVertex2f(x + width, y);
		glTexCoord2f(0, 1);// Left bottem
		glVertex2f(x + width, y  + height);

		glTexCoord2f(0, 1); // Left bottom
		glVertex2f(x + width, y  + height);
		glTexCoord2f(1, 1); // Right bottom
		glVertex2f(x, y  + height);
		glTexCoord2f(1, 0); // Right top
		glVertex2f(x, y);
		glEnd();
		tex.unbind();
	}
}
