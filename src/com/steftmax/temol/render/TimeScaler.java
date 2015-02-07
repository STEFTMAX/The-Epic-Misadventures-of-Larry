package com.steftmax.temol.render;

public class TimeScaler {
	public static double getTimeScaledDouble(double input, long deltaNanos) {
		return deltaNanos / 1000000000d * input;
	}

	public static float getTimeScaledFloat(float input, long deltaNanos) {
		return deltaNanos / 1000000000f * input;
	}
	
	public static float nanosToMilisF(long input) {
		return input / 1000000f;
	}
	
	public static double nanosToMilisD(long input) {
		return input / 1000000d;
	}
	
	public static double nanosToSecondsD(long input) {
		return input / 1000000000d;
	}
	
	public static float nanosToSecondsF(long input) {
		return input / 1000000000f;
	}
}
