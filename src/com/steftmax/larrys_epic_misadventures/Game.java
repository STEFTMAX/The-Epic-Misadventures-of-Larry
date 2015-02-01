package com.steftmax.larrys_epic_misadventures;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.draw.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.draw.Window;
import com.steftmax.larrys_epic_misadventures.state.State;
import com.steftmax.larrys_epic_misadventures.update.DeltaTimer;

/**
 * The class that should be overridden by any game using this engine.
 * 
 * @author pieter3457
 *
 */
public abstract class Game implements Runnable {

	private boolean stop = false;

	private DeltaTimer timer;
	private double timeScale;

	private boolean setup = false;

	private long maxBetweenFrameNanos;

	public static Window WINDOW;
	
	private State currentState;

	// private int maxfps;
	
	public void setup(Window w, double timeScale, boolean vSync,
			long maxBetweenFrameNanos, State beginningState) {

		if (this.setup) {
			System.err.println("Game already setup!!!");
			return;
		}

		this.currentState = beginningState;
		
		Game.WINDOW = w;
		this.timeScale = timeScale;

		this.timer = new DeltaTimer(timeScale);

		this.maxBetweenFrameNanos = maxBetweenFrameNanos;

		Display.setVSyncEnabled(vSync);

		// Set setup to true
		this.setup = true;
	}

	@Override
	public void run() {
		init();
		if (!setup) {
			System.err
					.println("Game not setup, setup the game with method setup(args...) please.");
			System.exit(-1);
		}
		// SleepMachine sleeper = new SleepMachine(maxfps);
		SpriteBatch batch = new SpriteBatch(1024, WINDOW.width, WINDOW.height);
		while (!stop && !Display.isCloseRequested()) {

			// sleeper.begin();

			long delta = timer.getDelta();

			if (delta > maxBetweenFrameNanos)
				delta = maxBetweenFrameNanos;
			currentState.update((long) (delta * timeScale));
			currentState.draw(batch);
		}
		destroy();
	}

	/**
	 * This method initializes the game that extends this class
	 */
	public abstract void init();

	/**
	 * @param delta
	 */
	protected abstract void update(long delta);

	/**
	 * Destroys the game
	 */
	public abstract void destroy();

	public synchronized void stop() {
		stop = true;
	}

	public synchronized void start() {
		new Thread(this).start();
	}
	
	public synchronized void changeState(State newState) {
		currentState = newState;
	}

	public static int getWidth() {
		return WINDOW.width;
	}

	public static int getHeight() {
		return WINDOW.height;
	}
}
