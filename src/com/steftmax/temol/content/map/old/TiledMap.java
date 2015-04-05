package com.steftmax.temol.content.map.old;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;

import com.steftmax.temol.graphics.Drawable;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.Updatable;
import com.steftmax.temol.resource.ResourceManager;

public class TiledMap implements Drawable, Updatable {

	public final Tile[][] tiles;
	private final int gridWidth, gridHeight;
	private final TextureRegion s;
	private ArrayList<Vector2> lightPositions = new ArrayList<Vector2>();

	public TiledMap(MapData data, ResourceManager rm) {
		this.tiles = data.tiles;
		this.gridWidth = data.gridWidth;
		this.gridHeight = data.gridHeight;
		this.s = new TextureRegion(rm.getTexture("/gfx/light.png"));
		lightPositions.add(new Vector2(100, 100));
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
	public void draw(SpriteBatch batch) {
		// int width = tileWidth;
		// int height = tileHeight;
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				tiles[y][x].draw(batch);
			}
		}

	}

	@Override
	public void update(float delta) {

	}

	private HashSet<Tile> returntiles = new HashSet<>();

	public synchronized Tile[] collidingTiles(int pointx, int pointy,
			int width, int height) {

		returntiles.clear();

		int x = (int) Math.floor(pointx / gridWidth);
		int dx = x - (int) Math.floor((pointx + width) / gridWidth);
		int y = (int) Math.floor(pointy / gridHeight);
		int dy = y - (int) Math.floor((pointy + height) / gridHeight);

		for (int i = 0; i <= dy; i++) {
			for (int j = 0; j <= dx; j++) {
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
	 * @param aabb
	 * @return
	 */
	public boolean isOnGround(AABB aabb) {
		return collidesMap(aabb.x, aabb.y - 1, aabb.width, aabb.height);
	}

	public void correctPosition(Vector2 oldPos, Vector2 newPos, int width, int height) {
		Tile[] possibleCollide = collidingTiles(rect)
		
	}

	/**
	 * @param batch
	 */
	public void drawLights(SpriteBatch batch) {
		final int width = s.regionWidth / 2;
		final int height = s.regionHeight / 2;
		for (Vector2 light : lightPositions) {
//			batch.draw(s, light.x - width, light.x + width, light.y - height,
//					light.y + height, false, false);
		}
	}
}
