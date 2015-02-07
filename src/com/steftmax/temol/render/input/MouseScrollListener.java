package com.steftmax.temol.render.input;

/**
 * @author pieter3457
 *
 */
public interface MouseScrollListener extends MouseListener{
	/**
	 * This method is called by the MouseInput when there is a scroll change.
	 * @param scrollChange The amount of clicks the wheel moved since last update.
	 */
	void onScroll(int scrollChange);
}
