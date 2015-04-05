package com.steftmax.temol.render.input;

import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.steftmax.temol.graphics.Camera;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.Updatable;
import com.steftmax.temol.resource.Settings;

public class MouseInput implements Updatable {

	private HashSet<MouseClickListener> clickListeners = new HashSet<MouseClickListener>();
	private HashSet<MouseScrollListener> scrollListeners = new HashSet<MouseScrollListener>();
	private HashSet<MousePositionListener> positionListeners = new HashSet<MousePositionListener>();
	public final Vector2 position = new Vector2();
	private float sensitivity;
	private Camera camera;
	
	private boolean primaryButton, secondaryButton;

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

	public void center() {
		this.position.set(Settings.getWidth() / 2, Settings.getHeight() / 2);
	}

	public void clear() {
		position.set(0, 0);
		camera = null;
		clearListeners();
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
	public void update(float delta) {

		final int width = Settings.getWidth();
		final int height = Settings.getHeight();

		primaryButton =  Mouse.isButtonDown(0);
		secondaryButton =Mouse.isButtonDown(1);
		
		while (Mouse.next()) {
			// update position 
			if (!Mouse.isGrabbed()) {
				updateMousePosition(Mouse.getEventX(),Mouse.getEventY());
				
			} else  {
				position.add(Mouse.getEventDX(), Mouse.getEventDY(),
						sensitivity);

				if (position.x < 0) {
					position.x = 0;
				} else {
					if (position.x >= width) {
						position.x = width - 1;
					}
				}
				if (position.y < 0) {
					position.y = 0;
				} else {
					if (position.y >= height) {
						position.y = height - 1;
					}
				}

			}
			
			for (MousePositionListener listener : positionListeners) {
				listener.onPositionUpdate((int) position.x, (int) position.y);
			}
			
			final int button = Mouse.getEventButton();
			// Keyevents
			if (button >= 0) {

				if (Mouse.getEventButtonState()) {
					
					if (button == 0) primaryButton = true;
					if (button == 1) secondaryButton = true;
					
					
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
			//ScrollEvents
			if (scroll != 0) {
				for (MouseScrollListener listener : scrollListeners) {
					listener.onScroll(scroll);
				}
			}
		}
		
	}

	public Vector2 getMousePosition() {
		return position;
	}

	private void updateMousePosition(int mouseX, int mouseY) {
		if (camera != null) {
			position.set(
					(mouseX + camera.getX() * camera.getScale())
							/ camera.getScale(), (mouseY + camera.getY()
							* camera.getScale())
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
	
	public boolean primaryDown() {
		return primaryButton;
	}
	public boolean secondaryDown() {
		return secondaryButton;
	}
}
