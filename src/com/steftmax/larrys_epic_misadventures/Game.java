package com.steftmax.larrys_epic_misadventures;

import org.lwjgl.opengl.Display;

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

//	private int maxfps;


	/**
	 * Sets up the Game with the parameters specified
	 * @param window 
	 * @param camera
	 * @param timeScale
	 * @param ki
	 * @param mi
	 */
	public void setup(double timeScale, boolean vSync) {
		
		if (this.setup){
			System.err.println("Game already setup!!!");
			return;
		}
		this.timeScale = timeScale;
		
		this.timer = new DeltaTimer(timeScale);
		
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
		//SleepMachine sleeper = new SleepMachine(maxfps);

		while (!stop && !Display.isCloseRequested()) {
			
			//sleeper.begin();
			
			long delta = timer.getDelta();
			
			update((long) (delta*timeScale));
			
			//sleeper.end();
			

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


}
