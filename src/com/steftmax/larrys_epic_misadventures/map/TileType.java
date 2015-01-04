package com.steftmax.larrys_epic_misadventures.map;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.steftmax.larrys_epic_misadventures.physics.CollisionChecker;
import com.steftmax.larrys_epic_misadventures.resource.Loadable;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

public class TileType implements Loadable {

	//public final Texture texture;
	public final boolean hasSolid;
	private final int id;
	public int height;
	public int width;
	
	
	private Sprite sprite;
	Texture texture;
	//TODO do stuff with the collideMap
	private boolean[][] collideMap;

	
	private final static HashMap<Integer, TileType> tileTypes = new HashMap<Integer, TileType>();
	

	public TileType(int id, boolean hasSolid, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
		this.hasSolid = hasSolid;
	}

	
	
	/**
	 * Creates an air Tile
	 * @param id
	 * @param width
	 * @param height
	 */
	public TileType(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.sprite = null;
		this.hasSolid = false;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */
	@Override
	public void load() {
		if (sprite!= null){
			texture = new Texture(sprite);
			texture.load();
			this.width = texture.width;
			this.height = texture.height;
			
			
			if (hasSolid) {
				
				BufferedImage img = sprite.getImage();
				collideMap = CollisionChecker.generateCollideMap(img);
				
			}
		}
	
		tileTypes.put(id, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		if (texture != null) texture.unload();
		
		tileTypes.remove(id);
	}

	public static TileType get(int id) {
		return tileTypes.get(id);
	}
}
