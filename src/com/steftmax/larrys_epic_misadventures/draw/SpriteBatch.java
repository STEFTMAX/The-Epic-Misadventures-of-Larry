package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.HashSet;

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
	private boolean testForContainment;

	public SpriteBatch(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(.5f, .5f, .5f, 1f);

		glDisable(GL_DEPTH_TEST);

		glLoadIdentity();
	}

	public void begin() {
		glClear(GL_COLOR_BUFFER_BIT);
		testForContainment = false;
	}

	public void begin(AABB aim) {
		glClear(GL_COLOR_BUFFER_BIT);
		this.aim = aim;
		testForContainment = true;
	}

	public void add(Sprite s) {
		if (testForContainment
				&& aim.collides((int) Math.floor(s.pos.x),
						(int) Math.floor(s.pos.y), s.width, s.height)) {
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
		for (Texture t: drawingBuffer.keySet()) {
			t.bind();
			for (Sprite s : drawingBuffer.get(t)) {
				
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
			t.unbind();
		}
		drawingBuffer.clear();
	}

}
