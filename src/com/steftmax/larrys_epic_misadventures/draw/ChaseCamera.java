package com.steftmax.larrys_epic_misadventures.draw;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.input.MouseScrollListener;
import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.math.Vector2;

public class ChaseCamera implements Camera, MouseScrollListener {

	// TODO maybe create ingame update events for resolution changes

	private Vector2 pos = new Vector2(0, 0);
	private float zoom = 3f;
	private final float maxZoom, minZoom;

	// these are perfect like this. for all games.
	private static final float yLookSensitivity = .56f,
			xLookSensitivity = .76f;

	private final float sensitivity;
	private MouseInput mi;
	private final int standardResolutionHeight, standardResolutionWidth;
	private AABB viewingArea = new AABB();

	public ChaseCamera(MouseInput mi, int standardResolutionWidth,
			int standardResolutionHeight, float maxZoom, float minZoom,
			float scrollSensitivity) {

		mi.addListener(this);

		this.maxZoom = maxZoom;
		this.minZoom = minZoom;
		this.standardResolutionWidth = standardResolutionWidth;
		this.standardResolutionHeight = standardResolutionHeight;
		this.sensitivity = scrollSensitivity;

		this.mi = mi;
	}

	private float getScaledZoom(final float zoom) {
		final float realXZoom = zoom * Game.getWidth()
				/ standardResolutionWidth;
		final float realYZoom = zoom * Game.getHeight()
				/ standardResolutionHeight;
		return (realXZoom + realYZoom) / 2f;
	}

	private float getScaledMaxZoom() {
		return getScaledZoom(maxZoom);
	}

	private float getScaledMinZoom() {
		return getScaledZoom(minZoom);
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

		final float maxZoom = getScaledMaxZoom();
		if (zoom > maxZoom) {
			zoom = maxZoom;
		}

		final float minZoom = getScaledMinZoom();
		if (zoom < minZoom) {
			zoom = minZoom;
		}
	}

	@Override
	public void beginFocus() {
		GL11.glPushMatrix();

		final int halfWindowWidth = Game.WINDOW.width / 2, halfWindowHeight = Game.WINDOW.height / 2;

		final float xMovement = zoom * pos.x
				+ ((int) mi.getMousePosition().x - halfWindowWidth)
				* xLookSensitivity;
		final float yMovement = zoom * pos.y
				+ ((int) mi.getMousePosition().y - halfWindowHeight)
				* yLookSensitivity;

		final float xDrift = halfWindowWidth - xMovement;
		final float yDrift = halfWindowHeight - yMovement;

		viewingArea.setBounds((int) Math.floor(xDrift / -zoom),
				(int) Math.floor(yDrift / -zoom),
				(int) Math.ceil(Game.WINDOW.width),
				(int) Math.ceil(Game.WINDOW.height));
		
		GL11.glTranslatef(xDrift, yDrift, 0);

		GL11.glScalef(zoom, zoom, 1f);
	}

	@Override
	public void endFocus() {

		GL11.glPopMatrix();
	}

	public void lock(Vector2 position) {
		pos = position;
	}

	public AABB getViewingArea() {
		return viewingArea;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#getScale()
	 */
	@Override
	public float getScale() {
		return zoom;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#getX()
	 */
	@Override
	public float getX() {
		return viewingArea.x;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#getY()
	 */
	@Override
	public float getY() {
		return viewingArea.y;
	}
}
