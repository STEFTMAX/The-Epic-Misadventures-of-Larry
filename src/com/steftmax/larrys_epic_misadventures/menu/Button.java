package com.steftmax.larrys_epic_misadventures.menu;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;

import com.steftmax.larrys_epic_misadventures.input.MouseClickListener;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 *
 */
public class Button implements MouseClickListener, Drawable {

	private AABB boundaryBox;
	private Texture texture;
	private boolean isClicked = false;

	public Button(MouseInput mi, AABB boundaryBox, Texture buttonImage) {
		mi.addListener(this);
		this.boundaryBox = boundaryBox;
		this.texture = buttonImage;
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
				isClicked = true;
				System.out.println("pressed!");
				// TODO perform the action
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
		if (isClicked && button == 0) {
			isClicked = false;
			System.out.println("released!");
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
	public void draw() {
		// TODO Auto-generated method stub

	}
}
