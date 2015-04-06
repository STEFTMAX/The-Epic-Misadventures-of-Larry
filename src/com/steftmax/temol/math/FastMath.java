package com.steftmax.temol.math;

/**
 * A utility class that simplifies hard math. Haha
 * 
 * @author pieter3457
 *
 */
public class FastMath {

	public final static float PI = (float) Math.PI;
	private final static int NSAMPLES = 500;
	private static double STEPSIZE;
	private final static float[] SAMPLES = new float[NSAMPLES];

	static {
		STEPSIZE = (2d * Math.PI) / NSAMPLES;
		for (int i = 0; i < NSAMPLES; i++) {
			SAMPLES[i] = (float) Math.sin(STEPSIZE * i);
		}
	}

	public static float sin(float radians) {
		int i = (int) (radians / STEPSIZE);
		i %= NSAMPLES;
		if (i < 0) {
			i += NSAMPLES;
		}
		return SAMPLES[i];
	}

	public static float cos(float radians) {
		return sin(.5f * PI - radians);
	}
}
