package com.steftmax.larrys_epic_misadventures.physics;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author pieter3457 A grid that stores all collidables for collision purposes
 */
// TODO create a map or something that stores positions in the map that are
// used.
public class Grid {

	public final int cellHeight;
	public final int cellWidth;
	private ArrayList<Collidable>[][] collidableMap;

	/**
	 * @param gridWidth
	 * @param gridHeight
	 */
	@SuppressWarnings("unchecked")
	public Grid(int cellWidth, int cellHeight, int mapWidth, int mapHeight) {
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.collidableMap = new ArrayList[(int) Math
				.ceil(mapWidth / cellWidth)][(int) Math.ceil(mapHeight
				/ cellHeight)];
	}

	public Grid(int cellSize, int mapWidth, int mapHeight) {
		this(cellSize, cellSize, mapWidth, mapHeight);
	}

	/**
	 * @param x
	 * @param y
	 * @return the left upper corner cell number
	 */
	@Deprecated
	public PointI getCellPos(int x, int y) {
		return new PointI((int) Math.floor(x / cellWidth), (int) Math.floor(y
				/ cellHeight));
	}

	public int getCellX(int x) {
		return (int) Math.floor(x / cellWidth);
	}

	public int getCellY(int y) {
		return (int) Math.floor(y / cellHeight);
	}

	// TODO bounds checking, here or somewhere else
	public void add(Collidable c) {
		Rectangle rect = c.getHitbox();

		// if (rect.width > cellWidth || rect.height > cellHeight) {

		int cellx = getCellX(rect.x);
		int celly = getCellY(rect.y);
		int dx = getCellX(rect.x + rect.width) - cellx;
		int dy = getCellY(rect.y + rect.height) - celly;
		for (int x = 0; x < dx + 1; x++) {
			for (int y = 0; y < dy + 1; y++) {
				if (collidableMap[x + cellx][y + celly] == null) {
					collidableMap[x + cellx][y + celly] = new ArrayList<Collidable>();
				}
				collidableMap[x + cellx][y + celly].add(c);
			}
		}
	}

	public void checkCollisions() {
		for (int x = 0; x < collidableMap.length; x++) {
			for (int y = 0; y < collidableMap[x].length; y++) {
				ArrayList<Collidable> collidables = collidableMap[x][y];
				if (collidables != null && collidables.size() > 1) {

					for (int i = 0; i < collidables.size(); i++) {
						for (int j = 0; j < collidables.size(); j++) {
							if (i!=j) {
							Collidable c1 = collidables.get(i);
							Collidable c2 =  collidables.get(j);
							Rectangle box1 = c1.getHitbox();
							Rectangle box2 = c2.getHitbox();
							// System.out.println(box1);
							// System.out.println(box2);
							if (CollisionChecker.boxCollides(box1.x, box1.y,
									box1.width, box1.height, box2.x, box2.y,
									box2.width, box2.height)) {
								c1.setPreviousPosition();
								c2.setPreviousPosition();
							}
							}
						}
					}
				}
			}
		}
	}

	public void clear() {
		for (int x = 0; x < collidableMap.length; x++) {
			for (int y = 0; y < collidableMap[x].length; y++) {
				if (collidableMap[x][y] != null) {
					collidableMap[x][y].clear();
				}
			}
		}
	}

}
