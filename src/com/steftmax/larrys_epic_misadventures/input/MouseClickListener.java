package com.steftmax.larrys_epic_misadventures.input;

/**
 * @author pieter3457
 *
 */
public interface MouseClickListener {
	
	
	/**
	 * This method is called by a MouseInput if one of the buttons is clicked.
	 * @param button The button clicked.
	 * @param x The x of the place where the button is clicked.
	 * @param y The y of the place where the button is clicked.
	 */
	public void onClick(int button, int x, int y);
	// TODO more mouseInputListener methods
}
