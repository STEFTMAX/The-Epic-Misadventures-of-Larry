package com.steftmax.larrys_epic_misadventures.draw;

import java.util.HashSet;
import java.util.Set;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.math.Vector2F;
import com.steftmax.larrys_epic_misadventures.physics.Point;
import com.steftmax.larrys_epic_misadventures.physics.Scale;

public class Drawer {
	
	private Camera camera;
	@SuppressWarnings("unused")
	private Window window;
	private Level level;
	//TODO replace with spritebatch
	private Set<Drawable> drawableBuffer = new HashSet<Drawable>();
	
	public enum DrawPriority{
		FRONT, MIDDLE, BACK
	}

	public Drawer(Level level, Window window, Camera camera){
		
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, window.width, window.height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClearColor(.5f, .5f, .5f, 1);
		
		glDisable(GL_DEPTH_TEST);
		
		glLoadIdentity();
		
		this.window = window;
		this.camera = camera;
		this.level = level;
	}

	public void draw() {
		
		Set<LevelObject> set = level.getLevelObjects();
		
		glClear(GL_COLOR_BUFFER_BIT);

		if (camera != null) {
			camera.beginFocus();
		}

		for (LevelObject obj : set) {
			if (obj instanceof Drawable) {
				drawableBuffer.add((Drawable) obj);
			}
		}
		
		drawPrioritized(DrawPriority.BACK, drawableBuffer);
		drawPrioritized(DrawPriority.MIDDLE, drawableBuffer);
		drawPrioritized(DrawPriority.FRONT, drawableBuffer);
		if (camera != null) {
			camera.endFocus();
		}
//		window.update();
		Display.update();
		drawableBuffer.clear();
	}

	public Camera getCamera() {
		return camera;
	}
	
	private void drawPrioritized(DrawPriority dp, Set<Drawable> set) {
		if (set != null) {
			for (Drawable obj : set) {
				obj.draw(dp);
			}
		}
	}

	public void resetCamera() {
		camera.setPosition(new Vector2F(0, 0));
		camera.setScale(new Scale(1));
	}

}
