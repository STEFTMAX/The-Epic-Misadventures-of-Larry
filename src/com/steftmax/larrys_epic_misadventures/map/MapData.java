package com.steftmax.larrys_epic_misadventures.map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.steftmax.larrys_epic_misadventures.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class MapData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2868286445513228431L;
	public final int[][] tiles;
	private final int tileWidth, tileHeight;
	
	public MapData(int[][] tiles, int tileWidth, int tileHeight) {
		this.tiles = tiles;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		
	}

	/**
	 * @return the tiles
	 */
	public int[][] getTiles() {
		return tiles;
	}

	/**
	 * @return the tileWidth
	 */
	public int getTileWidth() {
		return tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public int getTileHeight() {
		return tileHeight;
	}
	
	/**
	 * Writes the MapData to the specified path.
	 * @param path The path to write to.
	 */
	public void write(final String path) {
		ObjectOutputStream stream = null;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(path));
			stream.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Loads a MapData from the specified path.
	 * @param path The path to load from.
	 * @return The loaded MapData
	 */
	public static MapData load(final String path) {
		MapData data = null;
		ObjectInputStream stream = null;
		try {
			stream = new ObjectInputStream(ResourceLoader.load(path));
			data = (MapData) stream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

}

