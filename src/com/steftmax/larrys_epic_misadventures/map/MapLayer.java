package com.steftmax.larrys_epic_misadventures.map;

import java.awt.image.BufferedImage;

import com.steftmax.larrys_epic_misadventures.draw.SpriteBatch;

/**
 * @author pieter3457
 *
 */
public class MapLayer {
	float xScaleSpeed, yScaleSpeed;
	BufferedImage layerImage;

	public MapLayer(BufferedImage layerImage, float xScaleSpeed,
			float yScaleSpeed) {
		this.xScaleSpeed = xScaleSpeed;
		this.yScaleSpeed = yScaleSpeed;
		this.layerImage = layerImage;
	}

	/**
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
}
