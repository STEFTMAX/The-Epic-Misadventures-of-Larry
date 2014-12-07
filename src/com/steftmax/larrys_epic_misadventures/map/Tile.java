package com.steftmax.larrys_epic_misadventures.map;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.steftmax.larrys_epic_misadventures.physics.CollisionChecker;
import com.steftmax.larrys_epic_misadventures.resource.Loadable;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

public class Tile implements Loadable {

	//public final Texture texture;
	public final boolean hasSolid;
	private final int id;
	private int height, width;
	
	
	private Sprite sprite;
	Texture texture;
	private boolean[][] collideMap;

	
	private final static HashMap<Integer, Tile> tileVoid = new HashMap<Integer, Tile>();
	

	public Tile(int id, boolean hasSolid, Sprite sprite) {
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
	public Tile(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.sprite = null;
		this.hasSolid = false;
	}

	public int getWidth() {
		if (sprite != null) {
			return texture.width;
		} else {
			return width;
		}

	}

	public int getHeight() {
		if (texture != null) {
			return texture.height;
		} else {
			return height;
		}
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
			if (hasSolid) {
				
				BufferedImage img = sprite.getImage();
				collideMap = CollisionChecker.generateCollideMap(img);
				
			}
		}
	
		tileVoid.put(id, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		if (texture != null) texture.unload();
		
		tileVoid.remove(id);
	}

	public static Tile getTile(int id) {
		return tileVoid.get(id);
	}
}
