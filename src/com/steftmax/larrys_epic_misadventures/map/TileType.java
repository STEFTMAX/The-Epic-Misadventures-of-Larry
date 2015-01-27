package com.steftmax.larrys_epic_misadventures.map;

import java.util.HashMap;

import com.steftmax.larrys_epic_misadventures.resource.ResourceManager;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;



public class TileType {

	//public final Texture texture;
	public final boolean hasSolid;
	//private final int id;
	public int height;
	public int width;
	
	
	public final Sprite sprite;
	
	public static final String TILESETPATH = "gfx/tiles.png";

	
	private final static HashMap<Integer, TileType> tileTypes = new HashMap<Integer, TileType>();
	
	public enum Types {
		GRASSBLOCK_LEFT(0),
		GRASSBLOCK_MIDDLE(1),
		GRASSBLOCK_RIGHT(2),
		DIRTBLOCK_LEFT(3),
		DIRTBLOCK_MIDDLE(4),
		DIRTBLOCK_RIGHT(5),
		PILLAR_TOP(6),
		PILLAR_MIDDLE(7),
		PILLAR_BOTTOM(8),
		STONE(9),
		STONE_PLATFORM(10);
		
		public final int id;
		public final boolean isSolid;
		
		private Types(int id) {
			this(id, true);
		}
		
		private Types(int id, boolean isSolid) {
			this.id = id;
			this.isSolid = isSolid;
		}
	}
	
	public static void init(ResourceManager rm) {
		
		for (Types t : Types.values()) {
			
			new TileType(t.id, t.isSolid, new Sprite(rm.getSpriteSheet(TILESETPATH).get(t.id)));
		}
		
	}

	public TileType(int id, boolean hasSolid, Sprite s) {
		//this.id = id;
		this.sprite = s;
		this.hasSolid = hasSolid;
		tileTypes.put(id, this);
	}


	public static TileType get(int id) {
		return tileTypes.get(id);
	}
}
