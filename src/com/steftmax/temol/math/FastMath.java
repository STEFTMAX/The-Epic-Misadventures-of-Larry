package com.steftmax.temol.math;

/**
 * A utility class that simplifies hard math. Haha
 * 
 * @author pieter3457
 *
 */
public class FastMath {

	public final static float PI = (float) Math.PI;
	public final static float PI2 = PI * 2;
	private final static int NSAMPLES = 400; //HHMMMMMMMMm
	private static double STEPSIZE;
	private final static float[] SAMPLES = new float[NSAMPLES];

	static {
		STEPSIZE = (.5d * Math.PI) / NSAMPLES;
		for (int i = 0; i < NSAMPLES; i++) {
			SAMPLES[i] = (float) Math.sin(STEPSIZE * i);
		}
	}

	public static float sin(float radians) {

		// normalize the radians to range 0 - 2 * pi
		radians = normalizeRadians(radians);
		int i = 0;
		boolean positive = radians < PI;

		if (!positive) {
			radians -= PI;
		}

		if (radians < PI * .5f) {
			i = (int) (radians / STEPSIZE);
		} else {
			if (radians < PI) {
				i = (int) ((PI - radians) / STEPSIZE);
			}
		}
		
		i %= NSAMPLES;

		return positive ? SAMPLES[i] : -SAMPLES[i];
	}

	public static float cos(float radians) {
		return sin(.5f * PI - radians);
	}

	public static float normalizeRadians(float radians) {
		radians %= PI2;

		if (radians < 0) {
			radians += PI2;
		}

		return radians;
	}
}
