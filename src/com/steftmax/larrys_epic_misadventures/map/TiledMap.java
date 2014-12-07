package com.steftmax.larrys_epic_misadventures.map;

import com.steftmax.larrys_epic_misadventures.draw.Drawable;
import com.steftmax.larrys_epic_misadventures.draw.GLGraphics;
import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;
import com.steftmax.larrys_epic_misadventures.level.LevelObject;
import com.steftmax.larrys_epic_misadventures.physics.Point;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class TiledMap extends LevelObject implements Drawable, Updatable{


	protected MapData data;

	public TiledMap(MapData data) {
		this.data = data;
	}

	public Tile getTile(Point pos) {
		int y = (int) Math.floor(pos.getAbsoluteY() / data.getTileHeight());
		int x = (int) Math.floor(pos.getAbsoluteX() / data.getTileWidth());
		if (y<0 || y >= data.tiles.length|| x<0 || x >= data.tiles[y].length) {
			return Tile.getTile(0);
		}
		return Tile.getTile(data.tiles[y][x]);
	}

	@Override
	public void draw(DrawPriority dp) {
		
		if (dp != DrawPriority.BACK) return;
		
		int width = data.getTileWidth();
		int height = data.getTileHeight();
		for (int y = 0; y < data.tiles.length; y++) {
			for (int x = 0; x < data.tiles[y].length; x++) {
				Texture t = Tile.getTile(data.tiles[y][x]).texture;
				if (t != null) GLGraphics.drawTexture(t, new Point(x * width, y * height));
			}
		}
	}

	@Override
	public void update(long delta) {

	}


}
