package com.steftmax.temol;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.input.KeyboardInput;
import com.steftmax.temol.input.MouseInput;
import com.steftmax.temol.render.DeltaTimer;
import com.steftmax.temol.resource.Settings;
import com.steftmax.temol.state.State;

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

	private float maxBetweenFrame;

	private State currentState;
	long renderCall = 0;

	private float avgFrameTime = 0;

	// TODO merge together in one input with an inputconfiguration object for
	// controls
	protected MouseInput mouseInput;
	protected KeyboardInput keyboardInput;

	// private int maxfps;

	public Game(float maxBetweenFrame) {

		this.timer = new DeltaTimer(timeScale);

		this.maxBetweenFrame = maxBetweenFrame;

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

			glMatrixMode(GL_PROJECTION);
			glOrtho(0, width, 0, height, 1, -1);
			glMatrixMode(GL_MODELVIEW);

			glEnable(GL_TEXTURE_2D);

			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

			glClearColor(0f, 0f, 0f, 1f);

			glDisable(GL_DEPTH_TEST);

			glLoadIdentity();
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
			float delta = timer.getDelta();
			avgFrameTime += (delta - avgFrameTime) / renderCall;

			if (delta > maxBetweenFrame)
				delta = maxBetweenFrame;

			keyboardInput.update(delta);
			mouseInput.update(delta);

			currentState.update(delta * timeScale);
			currentState.draw(batch);
			Display.update();
		}
		currentState.deleteResources();
		destroy();
		System.exit(0);
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

	public synchronized void changeState(State lastState,
			Class<? extends State> newState) {
		if (lastState != null) {
			lastState.deleteResources();
			mouseInput.clear();
			keyboardInput.clear();
		}
		avgFrameTime = 0;
		renderCall = 0;
		try {
			Constructor<? extends State> c = newState
					.getDeclaredConstructor(Game.class);
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
