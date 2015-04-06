package com.steftmax.temol.render.input;

import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.steftmax.temol.render.Updatable;

//TODO multiple keys with same function
//TODO timed precision
public class KeyboardInput implements Updatable {

	public int left = Keyboard.KEY_A, right = Keyboard.KEY_D,
			up = Keyboard.KEY_W, down = Keyboard.KEY_S,
			shift = Keyboard.KEY_LSHIFT;

	private boolean[] keys = new boolean[256];
	private HashSet<KeyboardListener> listeners = new HashSet<KeyboardListener>();
	private HashSet<KeyboardCharacterListener> characterListeners = new HashSet<KeyboardCharacterListener>();

	public KeyboardInput() {
		if (!Keyboard.isCreated()) {
			try {
				Keyboard.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isKeyDown(int key) {
		return Keyboard.isKeyDown(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Renderable#render(long)
	 */
	@Override
	public void update(float delta) {

		while (Keyboard.next()) {
			boolean pressed;
			int key;
			if (Keyboard.getEventKey() < keys.length) {

				pressed = Keyboard.getEventKeyState();
				key = Keyboard.getEventKey();

				for (KeyboardCharacterListener listener : characterListeners) {
					if (pressed) {
						listener.onCharacterPress(Keyboard.getEventCharacter());
					}
				}

				for (KeyboardListener listener : listeners) {
					if (pressed) {
						listener.onKeyPress(key);
					} else {
						listener.onKeyRelease(key);
						if (keys[key]) {
							listener.onKeyPressed(key);
						}
					}
				}

				keys[key] = pressed;
			}
		}
	}

	public boolean isLeftDown() {
		return keys[left];
	}

	public boolean isRightDown() {
		return keys[right];
	}

	public boolean isUpDown() {
		return keys[up];
	}

	public boolean isDownDown() {
		return keys[down];
	}

	public boolean isShiftDown() {
		return keys[shift];
	}

	public void clear() {
		listeners.clear();
		characterListeners.clear();
	}

	public void addListener(KeyboardListener listener) {
		listeners.add(listener);
	}

	public void removeListener(KeyboardListener listener) {
		listeners.remove(listener);
	}

	public void addListener(KeyboardCharacterListener listener) {
		characterListeners.add(listener);
	}

	public void removeListener(KeyboardCharacterListener listener) {
		characterListeners.remove(listener);
	}
}
