package com.steftmax.temol.render.state;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.render.input.MouseClickListener;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.render.input.MousePositionListener;

/**
 * @author pieter3457
 *
 */


public class Button implements MouseClickListener, MousePositionListener, Drawable {

	public interface Listener {
		public void onPress(Button b);
		
		public void onRelease(Button b);
		
		public void onPressed(Button b);
	}
	
	private enum State {
		PRESS, HOVER, IDLE
	}

	private AABB boundaryBox;
	private Sprite press, hover, idle;
	private State state = State.IDLE;
	
	private Listener listener;

	public Button(Listener listener, MouseInput mi, int x, int y, TextureRegion press,
			TextureRegion hover, TextureRegion idle) {
		this(listener, mi, new AABB(x, y, idle.regionWidth, idle.regionHeight), press, hover, idle);
	}

	public Button(Listener listener, MouseInput mi, AABB boundaryBox, TextureRegion press,
			TextureRegion hover, TextureRegion idle) {
		mi.addListener(this);
		
		this.listener = listener;
		
		this.boundaryBox = boundaryBox;

		this.press = new Sprite(press, boundaryBox.x, boundaryBox.y);
		this.hover = new Sprite(hover, boundaryBox.x, boundaryBox.y);
		this.idle = new Sprite(idle, boundaryBox.x, boundaryBox.y);
	}
	
	public void setDimensions(int width, int height) {
		press.setDimensions(width, height);
		hover.setDimensions(width, height);
		idle.setDimensions(width, height);
		boundaryBox.setDimensions(width, height);
	}
	
	public Button(Listener listener, MouseInput mi, AABB boundaryBox, Sprite press, Sprite hover, Sprite idle) {

		mi.addListener(this);
		
		this.listener = listener;
		
		this.boundaryBox = boundaryBox;

		this.press = press;
		this.hover = hover;
		this.idle = idle;
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
				listener.onPress(this);
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
				listener.onPressed(this);
				state = State.HOVER;
			} else {
				listener.onRelease(this);
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
			hover.draw(batch);
			break;
		case IDLE:
			idle.draw(batch);
			break;
		case PRESS:
			press.draw(batch);
			break;
		}
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
