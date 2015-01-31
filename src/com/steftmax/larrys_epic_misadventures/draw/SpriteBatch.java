package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.sprite.TextureRegion;

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

	float[] vertices;
	float[] textures;

	private int index = 0;

	public SpriteBatch(int size, int width, int height) {

		glMatrixMode(GL_PROJECTION);
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(.5f, .5f, .5f, 1f);

		glDisable(GL_DEPTH_TEST);

		glLoadIdentity();

		vertexData = BufferUtils.createFloatBuffer(size * vertexSize * 4);
		textureData = BufferUtils.createFloatBuffer(size * textureSize * 4);
		// four for the four corners of a quad
		vertices = new float[size * vertexSize * 4];
		textures = new float[size * textureSize * 4];
	}

	public void begin(AABB aim) {
		glClear(GL_COLOR_BUFFER_BIT);
		this.aim = aim;
	}
	
	public void draw(Sprite s) {
		if (!aim.collides((int) Math.floor(s.pos.x), (int) Math.floor(s.pos.y),
				s.width, s.height)) {
			return;
		}

		Texture t = s.getTexture();
		if (lastTexture != null && lastTexture != t) {
			flush();
		}
		
		lastTexture = t;
		
		final TextureRegion tr = s.texReg;

		float u1 = tr.left;
		float u2 = tr.right;
		float v1 = tr.top;
		float v2 = tr.bottom;

		float x1 = s.pos.x;
		float x2 = s.pos.x + s.width;
		float y1 = s.pos.y;
		float y2 = s.pos.y + s.height;

		if (s.flipY) {

			float tmp = u1;

			u1 = u2;
			u2 = tmp;
		}
		
		if (s.flipX) {
			float tmp = v1;
			
			v1 = v2;
			v2 = tmp;
		}
		// XY
		vertices[index] = x1;
		textures[index++] = u1;
		vertices[index] = y1;
		textures[index++] = v1;

		vertices[index] = x2;
		textures[index++] = u2;
		vertices[index] = y1;
		textures[index++] = v1;

		vertices[index] = x2;
		textures[index++] = u2;
		vertices[index] = y2;
		textures[index++] = v2;

		vertices[index] = x1;
		textures[index++] = u1;
		vertices[index] = y2;
		textures[index++] = v2;
	}

	public void end() {
		if (index > 0) {
			flush();
		}
	}

	private void flush() {
		if (index  <= 0) return;

		lastTexture.bind();
		
		vertexData.put(vertices, 0, index);
		vertexData.rewind();

		textureData.put(textures, 0, index);
		textureData.rewind();

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		glVertexPointer(2, 0, vertexData);
		glTexCoordPointer(2, 0, textureData);

		glDrawArrays(GL_QUADS, 0, index - 1);

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		lastTexture.unbind();
		index = 0;

	}
}
