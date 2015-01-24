package com.steftmax.larrys_epic_misadventures.input;

import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.math.Vector2;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class MouseInput implements Updatable {

	private HashSet<MouseClickListener> clickListeners = new HashSet<MouseClickListener>();
	private HashSet<MouseScrollListener> scrollListeners = new HashSet<MouseScrollListener>();
	public final Vector2 position = new Vector2();
	private float sensitivity;

	public MouseInput() {
		this(false, 1f);
	}

	public MouseInput(boolean grabbed, float sensitivity) {
		if (!Mouse.isCreated()) {
			try {
				Mouse.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}

		Mouse.setGrabbed(grabbed);
		this.sensitivity = sensitivity;
	}

	public MouseInput(boolean grabbed) {
		this(grabbed, 1f);
	}

	@Deprecated
	public int getMouseWheelChange() {
		return Mouse.getDWheel();
	}

	// Listener changing methods
	public void clearListeners() {
		clickListeners.clear();
		scrollListeners.clear();
	}

	public void addListener(MouseListener listener) {
		if (listener instanceof MouseClickListener) {
			clickListeners.add((MouseClickListener) listener);
		}
		if (listener instanceof MouseScrollListener) {
			scrollListeners.add((MouseScrollListener) listener);
		}
	}

	public void removeListener(MouseListener listener) {
		if (listener instanceof MouseClickListener) {
			clickListeners.remove((MouseClickListener) listener);
		}
		if (listener instanceof MouseScrollListener) {
			scrollListeners.remove((MouseScrollListener) listener);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.render.Renderable#render(long)
	 */
	@Override
	public void update(long delta) {
		while (Mouse.next()) {
			int button = Mouse.getEventButton();
			if (button >= 0) {
				// System.out.println("Mouse clicked at x: " + Mouse.getEventX()
				// + " and y: " + Mouse.getEventY());
				// TODO seperate click and declick
				if (Mouse.isButtonDown(button)) {
					for (MouseClickListener listener : clickListeners) {

						listener.onClick(button, Mouse.getEventX(),
								Game.WINDOW.height - Mouse.getEventY());
					}
				} else {
					for (MouseClickListener listener : clickListeners) {

						listener.onDeClick(button, Mouse.getEventX(),
								getMouseY());
					}
				}

			}
			int scroll = Mouse.getEventDWheel();
			if (scroll != 0) {
				for (MouseScrollListener listener : scrollListeners) {
					listener.onScroll(scroll);
				}
			}
			if (Mouse.isGrabbed()) {
				position.add(Mouse.getEventDX(), -Mouse.getEventDY(),
						sensitivity);

				if (position.x < 0) {
					position.x = 0;
				} else {
					if (position.x >= Game.WINDOW.width) {
						position.x = Game.WINDOW.width - 1;
					}
				}
				if (position.y < 0) {
					position.y = 0;
				} else {
					if (position.y >= Game.WINDOW.height) {
						position.y = Game.WINDOW.height - 1;
					}
				}

			}
		}
		if (!Mouse.isGrabbed()) {
			position.set(Mouse.getX(), getMouseY());
		}
	}

	private int getMouseY() {
		return Game.WINDOW.height - Mouse.getEventY();
	}

	public Vector2 getMousePosition() {
		return position;
	}

	public void unGrab() {
		Mouse.setGrabbed(false);
	}

	public void grab() {
		Mouse.setGrabbed(true);
	}
}
