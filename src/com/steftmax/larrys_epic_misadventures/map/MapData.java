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
public class MapData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2868286445513228431L;
	public final Tile[][] tiles;
	public final int gridWidth, gridHeight;

	// TODO not working when using differently sized tiles :) :P xD
	public MapData(int[][] tileTypeNumbers, int gridWidth, int gridHeight) {

		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

		tiles = new Tile[tileTypeNumbers.length][];
		for (int y = 0; y < tileTypeNumbers.length; y++) {
			tiles[y] = new Tile[tileTypeNumbers[y].length];
			for (int x = 0; x < tileTypeNumbers[x].length; x++) {
				tiles[y][x] = new Tile(TileType.get(tileTypeNumbers[y][x]), x
						* gridWidth, y * gridHeight);
			}
		}

	}

	public MapData(Tile[][] tiles, int gridWidth, int gridHeight) {
		this.tiles = tiles;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

	}

	/**
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * @return the gridWidth
	 */
	public int getgridWidth() {
		return gridWidth;
	}

	/**
	 * @return the gridHeight
	 */
	public int getgridHeight() {
		return gridHeight;
	}

	/**
	 * Writes the MapData to the specified path.
	 * 
	 * @param path
	 *            The path to write to.
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
	 * 
	 * @param path
	 *            The path to load from.
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
