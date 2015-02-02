package com.steftmax.larrys_epic_misadventures.map;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author pieter3457
 *
 */
public class MapData implements Serializable{
	ArrayList<BufferedImage> layers = new ArrayList<BufferedImage>();
	int width, height;
	ArrayList<BitSet> collisionMap = new ArrayList<BitSet>();
	
	public MapData() {
	}
	
}
