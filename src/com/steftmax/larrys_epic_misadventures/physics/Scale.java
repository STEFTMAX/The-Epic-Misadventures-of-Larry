package com.steftmax.larrys_epic_misadventures.physics;

public class Scale {
	
	private double metersPerPixel;

	public Scale(double metersPerPixel) {
		this.metersPerPixel = metersPerPixel;
	}

	public void zoom(double multiplier) {
		metersPerPixel *= multiplier;
	}

	public double getMetersPerPixel() {
		return metersPerPixel;
	}

	public double getPixelsPerMeter() {
		return 1 / metersPerPixel;
	}
}
