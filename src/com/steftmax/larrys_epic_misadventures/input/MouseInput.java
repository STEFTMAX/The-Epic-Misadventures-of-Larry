package com.steftmax.larrys_epic_misadventures.input;

import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.draw.Camera;
import com.steftmax.larrys_epic_misadventures.math.Vector2;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class MouseInput implements Updatable {

	private HashSet<MouseClickListener> clickListeners = new HashSet<MouseClickListener>();
	private HashSet<MouseScrollListener> scrollListeners = new HashSet<MouseScrollListener>();
	private HashSet<MousePositionListener> positionListeners = new HashSet<MousePositionListener>();
	public final Vector2 position = new Vector2();
	private float sensitivity;
	private Camera camera;

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
		positionListeners.clear();
	}

	public void addListener(MouseListener listener) {
		if (listener instanceof MouseClickListener) {
			clickListeners.add((MouseClickListener) listener);
		}
		if (listener instanceof MouseScrollListener) {
			scrollListeners.add((MouseScrollListener) listener);
		}
		if (listener instanceof MousePositionListener) {
			positionListeners.add((MousePositionListener) listener);
		}
	}

	public void removeListener(MouseListener listener) {
		if (listener instanceof MouseClickListener) {
			clickListeners.remove((MouseClickListener) listener);
		}
		if (listener instanceof MouseScrollListener) {
			scrollListeners.remove((MouseScrollListener) listener);
		}
		if (listener instanceof MousePositionListener) {
			positionListeners.remove((MousePositionListener) listener);
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
			final int button = Mouse.getEventButton();
			if (button >= 0) {
				// System.out.println("Mouse clicked at x: " + Mouse.getEventX()
				// + " and y: " + Mouse.getEventY());
				// TODO seperate click and declick
				updateMousePosition(Mouse.getEventX(), Game.WINDOW.height
						- Mouse.getEventY());
				if (Mouse.isButtonDown(button)) {

					for (MouseClickListener listener : clickListeners) {

						listener.onClick(button, (int) position.x,
								(int) position.y);
					}
				} else {
					for (MouseClickListener listener : clickListeners) {

						listener.onDeClick(button, (int) position.x,
								(int) position.y);
					}
				}

			}
			final int scroll = Mouse.getEventDWheel();
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
			updateMousePosition(Mouse.getX(), Game.WINDOW.height - Mouse.getY());
		}
		for (MousePositionListener listener : positionListeners) {
			listener.onPositionUpdate((int) position.x, (int) position.y);
		}
	}

	public Vector2 getMousePosition() {
		return position;
	}

	private void updateMousePosition(int mouseX, int mouseY) {
		if (camera != null) {
			position.set(
				(mouseX + camera.getX() * camera.getScale())
						/ camera.getScale(),
				(mouseY + camera.getY() * camera.getScale())
						/ camera.getScale());
		} else {
			position.set(mouseX, mouseY);
		}
		
	}

	public void unGrab() {
		Mouse.setGrabbed(false);
	}

	public void grab() {
		Mouse.setGrabbed(true);
	}

	/**
	 * @param cam
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
