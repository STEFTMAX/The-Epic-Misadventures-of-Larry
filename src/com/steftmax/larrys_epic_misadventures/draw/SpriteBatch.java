package com.steftmax.larrys_epic_misadventures.draw;

import java.util.HashMap;
import java.util.HashSet;

import com.steftmax.larrys_epic_misadventures.physics.Point;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 *
 */
public class SpriteBatch {
	
	private boolean hasBegun = false;
	
	private HashMap<Texture, HashSet<Point>>[] layers;
	public final int lastLayer;
	
	/**
	 * Creates a spritebatch with the specified amount of layers.
	 * @param layers The amount of layers.
	 */
	@SuppressWarnings("unchecked")
	public SpriteBatch(int layers) {
		this.layers = new HashMap[layers];
		lastLayer = layers - 1; 
		for (int i = 0; i < this.layers.length; i++) {
			this.layers[i] = new HashMap<Texture, HashSet<Point>>();
		}
	}
	
	public void begin() {
		if (hasBegun) {
			return;
		}
		hasBegun = true;
	}
	
	public void end() {
		if (!hasBegun) {
			return;
		}
		hasBegun  = false;
		
		for (int i = 0; i < layers.length; i ++) {
			for (Texture t : layers[i].keySet()){
				
			}
		}
	}
}
