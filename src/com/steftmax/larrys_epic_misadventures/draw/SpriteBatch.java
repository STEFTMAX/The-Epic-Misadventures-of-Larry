package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.HashSet;

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

	HashMap<Texture, HashSet<Sprite>> drawingBuffer = new HashMap<Texture, HashSet<Sprite>>();
	AABB aim;
	boolean directRender = false;

	FloatBuffer vertexData;
	int vertexSize = 4;

	float[] data;
	private int index;

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
		//four for the four corners of a quad
		data = new float[size*vertexSize * 4];
	}

	public void begin(AABB aim) {
		glClear(GL_COLOR_BUFFER_BIT);
		this.aim = aim;
		index = 0;
	}

	public void add(Sprite s) {
		if (!aim.collides((int) Math.floor(s.pos.x), (int) Math.floor(s.pos.y),
				s.width, s.height)) {
			return;
		}

		Texture t = s.getTexture();

		if (drawingBuffer.containsKey(t)) {
			drawingBuffer.get(t).add(s);
		} else {

			// TODO Set pooling
			final HashSet<Sprite> temp = new HashSet<Sprite>();
			temp.add(s);
			drawingBuffer.put(t, temp);
		}
	}

	public void end() {
		Texture texture = null;
		for (Texture t : drawingBuffer.keySet()) {
			texture = t;
			t.bind();
			for (Sprite s : drawingBuffer.get(t)) {
				if (directRender)
					drawImmediate(s);
				else
					addToBuffer(s);
			}
			t.unbind();
		}
		if (!directRender && texture != null) {
			texture.bind();
			drawVertexArrays();
			texture.unbind();
		}

		index = 0;

		drawingBuffer.clear();
	}

	private void addToBuffer(Sprite s) {
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
		// XY
		data[index ++] = x1;
		data[index ++] = y1;
		data[index ++] = u1;
		data[index ++] = v1;
		
		data[index ++] = x2;
		data[index ++] = y1;
		data[index ++] = u2;
		data[index ++] = v1;

		data[index ++] = x2;
		data[index ++] = y2;
		data[index ++] = u2;
		data[index ++] = v2;

		data[index ++] = x1;
		data[index ++] = y2;
		data[index ++] = u1;
		data[index ++] = v2;

	}

	
	
	private void drawVertexArrays() {
		vertexData.put(data, 0, index);
		vertexData.flip();
		
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		glVertexPointer(2, 0 , vertexData);
		glTexCoordPointer(2, 2*4, vertexData);

		glDrawArrays(GL_QUADS, 0, index);

		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		//vertexData.rewind();
		
	}

	private void drawImmediate(Sprite s) {
		final TextureRegion tr = s.texReg;

		float xleft = tr.left;
		float xright = tr.right;

		float ytop = tr.top;
		float ybottom = tr.bottom;

		float x1 = s.pos.x;
		float x2 = s.pos.x + s.width;
		float y1 = s.pos.y;
		float y2 = s.pos.y + s.height;

		if (s.flipY) {

			float xmiddle = xleft;

			xleft = xright;
			xright = xmiddle;
		}

		glBegin(GL_TRIANGLES);
		glTexCoord2f(xleft, ytop);// Right top
		glVertex2f(x1, y1);
		glTexCoord2f(xright, ytop);// Left top
		glVertex2f(x2, y1);
		glTexCoord2f(xright, ybottom);// Left bottem
		glVertex2f(x2, y2);

		glTexCoord2f(xright, ybottom); // Left bottom
		glVertex2f(x2, y2);
		glTexCoord2f(xleft, ybottom); // Right bottom
		glVertex2f(x1, y2);
		glTexCoord2f(xleft, ytop); // Right top
		glVertex2f(x1, y1);
		glEnd();
	}

}
