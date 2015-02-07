package com.steftmax.temol.render;

/**
 * @author pieter3457
 *
 */
public class SleepMachine {
	private final double sleepTime;
	private long beginTime;

	public SleepMachine(int maxfps) {
		this.sleepTime = 1000000000d / maxfps;
	}

	public void begin() {
		this.beginTime = System.nanoTime();
	}

	public void end() {
		long endTime = System.nanoTime();
		try {
			Thread.sleep(Math.max(0, (long) TimeScaler
					.nanosToMilisD((long) (sleepTime - (endTime - beginTime)))));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
