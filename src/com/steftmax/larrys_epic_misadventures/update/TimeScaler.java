package com.steftmax.larrys_epic_misadventures.update;

public class TimeScaler {
	public static double getTimeScaledDouble(double input, long deltaNanos) {
		return deltaNanos / 1000000000d * input;
	}

	public static float getTimeScaledFloat(float input, long deltaNanos) {
		return deltaNanos / 1000000000f * input;
	}
	
	public static float nanosToMilis(float input) {
		return input / 1000000f;
	}
	
	public static double nanosToMilis(double input) {
		return input / 1000000d;
	}
}
