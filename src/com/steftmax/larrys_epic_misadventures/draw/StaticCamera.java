package com.steftmax.larrys_epic_misadventures.draw;


import org.lwjgl.opengl.GL11;
/**
 * @author pieter3457
 *
 */
public class StaticCamera implements Camera{
	private final float x, y, zoom;

	public StaticCamera(float zoom, float x, float y) {
		this.zoom = zoom;
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#beginFocus()
	 */
	@Override
	public void beginFocus() {
		GL11.glPushMatrix();
		
		
		GL11.glScalef(zoom, zoom, 1f);
		GL11.glTranslatef(-x, -y, 0);
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#endFocus()
	 */
	@Override
	public void endFocus() {
		GL11.glPopMatrix();
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
		return x;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.draw.Camera#getY()
	 */
	@Override
	public float getY() {
		return y;
	}
	
}
