package com.sessionstraps.larrys_epic_misadventures;

import java.util.ArrayList;

import com.sessionstraps.game_engine.map.Tile;
import com.sessionstraps.game_engine.resources.Loadable;
import com.sessionstraps.game_engine.resources.Texture;
import com.sessionstraps.game_engine.sprite.Sprite;

/**
 * @author pieter3457
 *
 */
@Deprecated
public class TileManager implements Loadable{
	private Tile[] tileTypes;
	private final int tileWidth, tileHeight;
	
	public TileManager(int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	/**
	 * @return
	 */
	public Tile[] getTiles() {
		return tileTypes;
	}


	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */ // TODO Move to LevelResources Class
	@Override
	public void load() {
		ArrayList<Tile> temp = new ArrayList<Tile>();
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_grassblock_left.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_grassblock_middle.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_grassblock_right.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_dirtblock_left.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_dirtblock_middle.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_dirtblock_right.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_pillar_top.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_pillar_middle.png"))));
		temp.add(new Tile(true, new Texture(new Sprite("img/tile_pillar_bottom.png"))));
		
		tileTypes = temp.toArray(new Tile[0]);
	}


	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		for (Tile tile : tileTypes) {
			tile.texture.unload();
		}
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}
}
