package com.steftmax.temol.state;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.input.MouseClickListener;
import com.steftmax.temol.math.AABB;

/**
 * @author pieter3457
 *
 */
public class CheckBox implements MouseClickListener, Drawable {

	private AABB boundaryBox;
	private Sprite unchecked, checked;
	private boolean isChecked = false, isClicked = false;
	
	private Listener listener;

	public interface Listener {
		public void onStateChange(CheckBox c, boolean newState);
	}
	
	
	
	public CheckBox(Listener listener, AABB boundaryBox, TextureRegion unchecked,
			TextureRegion checked, boolean isChecked) {
		this.listener = listener;
		this.boundaryBox = boundaryBox;
		this.unchecked = new Sprite(unchecked, boundaryBox.x, boundaryBox.y);
		this.checked = new Sprite(checked, boundaryBox.x, boundaryBox.y);
		this.isChecked = isChecked;
	}

	public CheckBox(Listener listener, AABB boundaryBox, TextureRegion unchecked,
			TextureRegion checked) {
		this(listener, boundaryBox, unchecked, checked, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.graphics.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.graphics.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		if (isChecked) {
			batch.draw(checked);
		} else {
			batch.draw(unchecked);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.render.input.MouseClickListener
	 * #onClick(int, int, int)
	 */
	@Override
	public void onClick(int button, int x, int y) {
		if (button == 0 && boundaryBox.containsPoint(x, y)) {
			isClicked = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.render.input.MouseClickListener
	 * #onDeClick(int, int, int)
	 */
	@Override
	public void onDeClick(int button, int x, int y) {
		if (button == 0 && boundaryBox.containsPoint(x, y)) {
			if (isClicked == true) {
				isClicked = false;
				isChecked = !isChecked;
				listener.onStateChange(this, isChecked);
			}
		}
	}

}
