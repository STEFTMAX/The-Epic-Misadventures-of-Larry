package com.steftmax.temol.content.map;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

import com.steftmax.temol.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class MapData {
	ArrayList<MapLayer> layers = new ArrayList<MapLayer>();
	int width, height;
	int revision;
	final static String FILETYPE = "TEMoLMAP", DATA = "DATA", COLLIDELAYER = "CLYR", LAYER = "LYRN";
	final static String EXTENSION = ".lmp";

	BitSet collisionMap;

	public MapData(int width, int height) {
		this.width = width;
		this.height = height;
		collisionMap = new BitSet(width * height);
		collisionMap.set(0, width * height, true);
//		System.out.println(collisionMap.size());
	}

	/**
	 * Saves the mapdata relatively to the Game directory
	 * 
	 * @param path
	 *            The path relative to the Game directory.
	 */
	public void save(String path) {
		StringBuilder finalPath = new StringBuilder(path.toLowerCase());
		if (!path.endsWith(EXTENSION)) {
			finalPath.append(EXTENSION);
		}
		File f = new File(ResourceLoader.getPath(), finalPath.toString());
		try {
			DataOutputStream dos = null;
			try {

				// TODO seperate them all because if one fails, they all fail.
				dos = new DataOutputStream(new BufferedOutputStream(
						new FileOutputStream(f)));
				
				// write filetype
				dos.writeBytes(FILETYPE);
				// write width and height
				dos.writeInt(width);
				dos.writeInt(height);
				// Data section marker
				dos.writeBytes(DATA);
				//write collisionMap data
				dos.writeBytes(COLLIDELAYER);
				dos.writeInt(collisionMap.size());
				dos.write(collisionMap.toByteArray());
//				TODO write layer Data!
//				for (int i = 0 ; i < layers.size(); i ++) {
//					dos.writeBytes(LAYER);
//					dos.writeInt(i);
//				}
				
			} finally {
				dos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static MapData load(String path) {
		return new MapData(0,0);
	}

}
