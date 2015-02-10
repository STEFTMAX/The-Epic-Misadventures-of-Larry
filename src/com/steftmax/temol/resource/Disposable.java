package com.steftmax.temol.resource;

/**
 * A disposable object is an object whose resources can and have to be deleted when not longer used.
 * @author pieter3457
 *
 */
public interface Disposable {
	
	
	/**
	 * Dispose the objects' resources.
	 */
	public void dispose();
}
