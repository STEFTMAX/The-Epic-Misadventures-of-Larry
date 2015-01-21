package com.steftmax.larrys_epic_misadventures.draw;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.input.MouseScrollListener;
import com.steftmax.larrys_epic_misadventures.math.Vector2F;

public class ChaseCamera implements Camera, MouseScrollListener {

	private Vector2F pos = new Vector2F(0, 0);
	private float zoom = 2.5f;
	private static final float MAXZOOM = 5f, MINZOOM = 1f;
	private float sensitivity = 0.001f;
//	private AABB lockingBox;

	public ChaseCamera(MouseInput mi) {
		mi.addListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.input.MouseScrollListener#onScroll
	 * (int)
	 */
	@Override
	public synchronized void onScroll(int scrollChange) {

		zoom *= 1f + scrollChange * sensitivity;
		if (zoom > MAXZOOM) {
			zoom = MAXZOOM;
		}
		if (zoom < MINZOOM) {
			zoom = MINZOOM;
		}
	}

	@Override
	public void beginFocus() {
		GL11.glPushMatrix();
//		float x = (lockingBox.x + lockingBox.width / 2f);
//		float y = (lockingBox.y + lockingBox.height / 2f);
		GL11.glTranslatef(((-zoom * pos.x + (Game.WINDOW.width / 2f))),
				((-zoom * pos.y + (Game.WINDOW.height / 2f))), 0);
		GL11.glScalef(zoom, zoom, 0);
	}

	@Override
	public void endFocus() {

		GL11.glPopMatrix();
	}

//	public void lock(AABB boundaryBox) {
//		this.lockingBox = boundaryBox;
//	}
	
	public void lock(Vector2F position) {
		pos = position;
	}

	// public Rectangle getViewArea() {
	// return new Rectangle(pos.getRoundedX(), pos.getRoundedY(), , arg3)
	// } TODO
}
