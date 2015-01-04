package com.steftmax.larrys_epic_misadventures.map;

import java.awt.Rectangle;
import java.util.HashSet;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.physics.Vector2F;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class TiledMap extends LevelObject implements Drawable, Updatable {

	public final Tile[][] tiles;
	private final int gridWidth, gridHeight;

	public TiledMap(MapData data) {
		this.tiles = data.tiles;
		this.gridWidth = data.gridWidth;
		this.gridHeight = data.gridHeight;
	}

	public Tile getTile(float pointx, float pointy) {

		int x = (int) Math.floor(pointx / gridWidth);
		int y = (int) Math.floor(pointy / gridHeight);

		if (y < 0 || y >= tiles.length || x < 0 || x >= tiles[y].length) {
			return null;
		}
		return tiles[y][x];
	}

	@Override
	public void draw(DrawPriority dp) {
		if (dp != DrawPriority.BACK)
			return;

		// int width = tileWidth;
		// int height = tileHeight;
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				tiles[y][x].draw(dp);
			}
		}
	}

	@Override
	public void update(long delta) {

	}
	
	private HashSet<Tile> returntiles = new HashSet<>();
	public synchronized Tile[] collidingTiles(int pointx, int pointy, int width, int height) {
		
		returntiles.clear();
		
		int x = (int) Math.floor(pointx / gridWidth);
		int dx = x - (int) Math.floor((pointx + width) / gridWidth);
		int y = (int) Math.floor(pointy / gridHeight);
		int dy = y - (int) Math.floor((pointy + height) / gridHeight);
		
		for (int i= 0; i<= dy; i++) {
			for (int j= 0; j<= dx; j++) {
				returntiles.add(tiles[y + i][x + j]);
			}
		}
		return (Tile[]) returntiles.toArray();
	}

	public boolean collidesMap(int x, int y, int width, int height) {
		Tile leftTop = getTile(x, y);
		Tile rightTop = getTile(x + width, y);
		Tile leftBottom = getTile(x, y + height);
		Tile rightBottom = getTile(x + width, y + height);

		return (leftTop.hasSolid() || rightTop.hasSolid()
				|| rightBottom.hasSolid() || leftBottom.hasSolid());
	}

	public boolean collidesMap(Rectangle rect) {
		return collidesMap(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * @param hitbox
	 * @return
	 */
	public boolean isOnGround(Rectangle hitbox) {
		return collidesMap(hitbox.x, hitbox.y - 1, hitbox.width, hitbox.height);
	}

	public void correctPosition(Vector2F oldPos, Vector2F newPos, int width, int height) {
		Tile[] possibleCollide = collidingTiles(rect)
		
	}
}
