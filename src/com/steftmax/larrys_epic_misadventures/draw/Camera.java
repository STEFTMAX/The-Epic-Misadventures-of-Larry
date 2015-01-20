package com.steftmax.larrys_epic_misadventures.draw;

import java.awt.Graphics2D;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.input.MouseScrollListener;
import com.steftmax.larrys_epic_misadventures.math.Vector2F;
import com.steftmax.larrys_epic_misadventures.physics.Scale;

public class Camera implements MouseScrollListener{

	private Vector2F pos = new Vector2F(0, 0);
	private Scale scale = new Scale(3);
	private int width, height;

	public Camera(int width, int height, MouseInput mi) {
		System.out.println(width);
		System.out.println(height);
		this.width = width;
		this.height = height;
		mi.addListener(this);
	}

	public void lock(Vector2F newPos) {
		this.pos = newPos;
	}


	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.input.MouseScrollListener#onScroll(int)
	 */
	@Override
	public synchronized void onScroll(int scrollChange) {
		scale.zoom(1 - scrollChange * 0.001);
	}

	@Deprecated
	public void focus(Graphics2D g) {
		g.translate(
				((-scale.getMetersPerPixel() * pos.x + (width / 2))),
				((-scale.getMetersPerPixel() * pos.y + (height / 2))));
		g.scale(scale.getMetersPerPixel(), scale.getMetersPerPixel());
		// WORKS!!!!!
	}

	public void beginFocus() {
		GL11.glPushMatrix();
		GL11.glTranslated(
				((-scale.getMetersPerPixel() * pos.x + (width / 2))),
				((-scale.getMetersPerPixel() * pos.y + (height / 2))),
				0);
		GL11.glScaled(scale.getMetersPerPixel(), scale.getMetersPerPixel(), 0);
	}

	public void endFocus() {

		GL11.glPopMatrix();
	}

	public void zoom(double d) {
		scale.zoom(d);
	}

	public void setPosition(Vector2F position) {
		this.pos = position;
	}

	public void setScale(Scale scale2) {
		this.scale = scale2;
	}

	// public Rectangle getViewArea() {
	// return new Rectangle(pos.getRoundedX(), pos.getRoundedY(), , arg3)
	// } TODO
}
