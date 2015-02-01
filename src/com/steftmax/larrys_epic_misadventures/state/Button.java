package com.steftmax.larrys_epic_misadventures.state;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.draw.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.input.MouseClickListener;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.input.MousePositionListener;
import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.TextureRegion;

/**
 * @author pieter3457
 *
 */
public class Button implements MouseClickListener, MousePositionListener, Drawable {

	private enum State {
		PRESS, HOVER, IDLE
	}

	private AABB boundaryBox;
	private Sprite press, hover, idle;
	private State state = State.IDLE;
	private boolean isPressed = false;

	public Button(MouseInput mi, int x, int y, TextureRegion press,
			TextureRegion hover, TextureRegion idle) {
		this(mi, new AABB(x, y, idle.width, idle.height), press, hover, idle);
	}

	public Button(MouseInput mi, AABB boundaryBox, TextureRegion press,
			TextureRegion hover, TextureRegion idle) {
		mi.addListener(this);

		this.boundaryBox = boundaryBox;

		this.press = new Sprite(press, boundaryBox.x, boundaryBox.y);
		this.hover = new Sprite(hover, boundaryBox.x, boundaryBox.y);
		this.idle = new Sprite(idle, boundaryBox.x, boundaryBox.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.input.MouseClickListener#onClick
	 * (int, int, int)
	 */
	@Override
	public void onClick(int button, int x, int y) {
		if (button == 0) {
			if (boundaryBox.containsPoint(x, y)) {
				this.state = State.PRESS;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.input.MouseClickListener#onDeClick
	 * (int, int, int)
	 */
	@Override
	public void onDeClick(int button, int x, int y) {
		if (state == State.PRESS && button == 0) {
			if (boundaryBox.containsPoint(x, y)) {
				state = State.HOVER;
				isPressed = true;
			} else {
				state = State.IDLE;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.draw.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.draw.Drawer.DrawPriority)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		switch (state) {
		case HOVER:
			batch.draw(hover);
			break;
		case IDLE:
			batch.draw(idle);
			break;
		case PRESS:
			batch.draw(press);
			break;
		}
	}

	public boolean consumePressed() {
		final boolean temp = isPressed;
		isPressed = false;
		return temp;
	}

	public void onPositionUpdate(int x, int y) {
		if (state != State.PRESS) {
			if (boundaryBox.containsPoint(x, y)) {
				state = State.HOVER;
			} else {
				state = State.IDLE;
			}
		}

	}
}
