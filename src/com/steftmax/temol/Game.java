package com.steftmax.temol;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.render.DeltaTimer;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.render.state.State;
import com.steftmax.temol.resource.Settings;

/**
 * The class that should be overridden by any game using this engine.
 * 
 * @author pieter3457
 *
 */
public abstract class Game implements Runnable {

	private boolean stop = false;

	private DeltaTimer timer;
	private float timeScale = 1f;

	private long maxBetweenFrameNanos;

	private State currentState;
	long renderCall = 0;

	private long avgFrameTime = 0;

	protected MouseInput mouseInput;

	protected KeyboardInput keyboardInput;

	// private int maxfps;

	public Game(long maxBetweenFrameNanos) {

		this.timer = new DeltaTimer(timeScale);

		this.maxBetweenFrameNanos = maxBetweenFrameNanos;

		initDisplay(Settings.getWidth(), Settings.getHeight(), false, null,
				null);

		this.mouseInput = new MouseInput(false,
				Settings.getIngameMouseSensitivity());
		this.keyboardInput = new KeyboardInput();
	}

	public void initDisplay(int width, int height, boolean vSync, String name,
			ByteBuffer[] icons) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(name);
			Display.setIcon(icons);
			Display.setResizable(false);
			Display.setVSyncEnabled(vSync);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		SpriteBatch batch = new SpriteBatch(1024, Settings.getWidth(),
				Settings.getHeight());
		start();

		while (!stop && !Display.isCloseRequested()) {

			renderCall++;
			long delta = timer.getDelta();
			avgFrameTime += (delta - avgFrameTime) / renderCall;

			if (delta > maxBetweenFrameNanos)
				delta = maxBetweenFrameNanos;
			keyboardInput.update(delta);
			mouseInput.update(delta);

			currentState.update((long) (delta * timeScale));
			currentState.draw(batch);
			Display.update();
		}
		currentState.deleteResources();
		destroy();
	}

	/**
	 * @param delta
	 */
	protected abstract void update(long delta);

	/**
	 * Destroys the game
	 */
	public abstract void destroy();

	public abstract void start();

	public synchronized void stop() {
		stop = true;
	}

	public synchronized void changeState(State lastState, Class<? extends State> newState) {
		if (lastState != null) {
			lastState.deleteResources();
			mouseInput.clear();
			keyboardInput.clear();
		}
		avgFrameTime = 0;
		renderCall = 0;
		try {
			Constructor<? extends State> c = newState.getDeclaredConstructor(Game.class);
			currentState = c.newInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MouseInput getMouseInput() {
		return mouseInput;
	}

	public KeyboardInput getKeyboardInput() {
		return keyboardInput;
	}
}
