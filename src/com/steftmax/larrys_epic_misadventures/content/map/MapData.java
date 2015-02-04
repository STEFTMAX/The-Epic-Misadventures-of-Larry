package com.steftmax.larrys_epic_misadventures.content.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author pieter3457
 *
 */
public class MapData implements Serializable{
	ArrayList<MapLayer> layers = new ArrayList<MapLayer>();
	int width, height;
	ArrayList<BitSet> collisionMap = new ArrayList<BitSet>();
	
	public MapData() {
	}
	
}
