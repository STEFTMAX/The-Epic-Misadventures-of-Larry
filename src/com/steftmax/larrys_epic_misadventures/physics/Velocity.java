package com.steftmax.larrys_epic_misadventures.physics;

import com.steftmax.larrys_epic_misadventures.update.TimeScaler;

public class Velocity {

	private float dx;
	private float dy;

	public Velocity(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public Point applyOnPosition(Point pos, long deltaNanos) {
		pos.translate(TimeScaler.getTimeScaledFloat(dx, deltaNanos), TimeScaler.getTimeScaledFloat(dy, deltaNanos));
		return pos;
	}

	public void stop() {
		dy = 0;
	}
	
	public double getHeading() {
		return Math.atan2(dy, dx);
	}

	public void translate(float ddx, float ddy) {
		dx += ddx;
		dy += ddy;
	}

	public void set(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
}
