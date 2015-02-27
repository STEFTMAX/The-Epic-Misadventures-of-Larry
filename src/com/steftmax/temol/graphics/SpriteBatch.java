package com.steftmax.temol.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertexPointer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.Texture;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.math.AABB;

/**
 * @author pieter3457
 *
 */
public class SpriteBatch {

	Texture lastTexture;
	AABB aim;

	FloatBuffer vertexData;
	int vertexSize = 2;

	FloatBuffer textureData;
	int textureSize = 2;

	FloatBuffer colorData;
	int colorSize = 4;

	float[] vertices;
	float[] textures;
	float[] colors;

	private int index = 0;
	private boolean drawing = false, containmentTest = false;
	
	private Color color = new Color(1, 1, 1, 1);

	public SpriteBatch(int size, int width, int height) {

		glMatrixMode(GL_PROJECTION);
		glOrtho(0, width, 0, height, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(0f, 0f, 0f, 1f);

		glDisable(GL_DEPTH_TEST);

		glLoadIdentity();

		vertexData = BufferUtils.createFloatBuffer(size * vertexSize * 4);
		textureData = BufferUtils.createFloatBuffer(size * textureSize * 4);
		colorData = BufferUtils.createFloatBuffer(size * colorSize * 4);
		// four for the four corners of a quad
		vertices = new float[size * vertexSize * 4];
		textures = new float[size * textureSize * 4];
		colors = new float[size * colorSize * 4];
	}

	// TODO conainmentTest upgrade
	public void begin(AABB aim) {
		drawing = true;
		glClear(GL_COLOR_BUFFER_BIT);
		this.aim = aim;
		containmentTest = aim != null;
	}

	public void begin() {
		begin(null);
	}

	public void draw(Sprite s) {
		draw(s.texReg, s.pos.x, s.pos.x + s.width * s.scaleX, s.pos.y, s.pos.y
				+ s.height * s.scaleY, s.flipX, s.flipY, s.containmentTest,
				s.color);
	}
	
	public void draw(final TextureRegion tr, float x1, float x2, float y1,
			float y2, boolean flipX, boolean flipY, boolean containmentTest) {
		draw(tr, x1, x2, y1, y2, flipX, flipY, containmentTest, this.color);
	}

	public void draw(final TextureRegion tr, float x1, float x2, float y1,
			float y2, boolean flipX, boolean flipY, boolean containmentTest,
			Color color) {
		if (!drawing) {
			System.err.println("Must call begin before drawing!");
			return;
		}

		if (this.containmentTest
				&& containmentTest
				&& !aim.collides((int) Math.floor(x1), (int) Math.floor(y1),
						(int) (x2 - x1), (int) (y2 - y1))) {

			return;
		}

		Texture t = tr.texture;
		if (lastTexture != null || lastTexture != t) {
			flush();
		}

		lastTexture = t;

		float u1 = tr.u1;
		float u2 = tr.u2;
		float v1 = tr.v1;
		float v2 = tr.v2;
		
		final float red = color.red;
		final float green = color.green;
		final float blue = color.blue;
		final float alpha = color.alpha;

		if (flipY) {

			final float tmp = u1;

			u1 = u2;
			u2 = tmp;
		}

		if (flipX) {
			final float tmp = v1;

			v1 = v2;
			v2 = tmp;
		}
		// Texture coordinates are always yFlipped
		vertices[index] = x1;
		colors[index * 2] = red;
		colors[index * 2 + 1] = green;
		textures[index++] = u1;
		vertices[index] = y1;
		colors[index * 2] = blue;
		colors[index * 2 + 1] = alpha;
		textures[index++] = v1;

		vertices[index] = x2;
		colors[index * 2] = red;
		colors[index * 2 + 1] = green;
		textures[index++] = u2;
		vertices[index] = y1;
		colors[index * 2] = blue;
		colors[index * 2 + 1] = alpha;
		textures[index++] = v1;

		vertices[index] = x2;
		colors[index * 2] = red;
		colors[index * 2 + 1] = green;
		textures[index++] = u2;
		vertices[index] = y2;
		colors[index * 2] = blue;
		colors[index * 2 + 1] = alpha;
		textures[index++] = v2;

		vertices[index] = x1;
		colors[index * 2] = red;
		colors[index * 2 + 1] = green;
		textures[index++] = u1;
		vertices[index] = y2;
		colors[index * 2] = blue;
		colors[index * 2 + 1] = alpha;
		textures[index++] = v2;
	}

	public void end() {
		flush();
		drawing = false;
	}

	public void flush() {
		if (index <= 0)
			return;

		lastTexture.bind();

		vertexData.put(vertices, 0, index);
		vertexData.rewind();

		textureData.put(textures, 0, index);
		textureData.rewind();

		colorData.put(colors, 0, index * 2);
		colorData.rewind();

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

		glVertexPointer(2, 0, vertexData);
		glTexCoordPointer(2, 0, textureData);
		glColorPointer(4, 0, colorData);

		glDrawArrays(GL_QUADS, 0, index - 1);

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		lastTexture.unbind();
		index = 0;

	}

	public void translate(float x, float y) {
		flush();
		glTranslatef(x, y, 0);
	}
}
