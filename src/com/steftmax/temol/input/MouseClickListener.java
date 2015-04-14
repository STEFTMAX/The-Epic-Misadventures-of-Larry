package com.steftmax.temol.input;

/**
 * @author pieter3457
 *
 */
public interface MouseClickListener extends MouseListener{
	
	
	/**
	 * This method is called by a MouseInput if one of the buttons is clicked.
	 * @param button The button that clicked.
	 * @param x The x of the place where the button is clicked.
	 * @param y The y of the place where the button is clicked.
	 */
	public void onClick(int button, int x, int y);
	
	/**
	 * This method is called by a MouseInput if one of the buttons is declicked.
	 * @param button The button that declicked.
	 * @param x The x of the place where the button is declicked.
	 * @param y The y of the place where the button is declicked.
	 */
	public void onDeClick(int button, int x, int y);
}
