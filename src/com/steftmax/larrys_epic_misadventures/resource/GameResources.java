package com.steftmax.larrys_epic_misadventures.resource;

import com.steftmax.larrys_epic_misadventures.map.old.TileType;
import com.steftmax.larrys_epic_misadventures.sprite.animation.PlaySequence;
import com.steftmax.larrys_epic_misadventures.sprite.animation.SwingPlaySequence;

/**
 * @author pieter3457
 *
 */
public class GameResources extends ResourceManager{

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.resource.loader.ResourceManager#load()
	 */
	@Override
	public void load() {
		
		loadSpriteSheet(TileType.TILESETPATH, 16, 16);
		
		loadAnimation("gfx/larry_walking.png", 13, 2, PlaySequence.REPEAT, 40, 0);
		loadAnimation("gfx/larry_breathing.png", 3, 1, new SwingPlaySequence(true), 2, 0);
		loadAnimation("gfx/larry_jumping.png", 5, 5, PlaySequence.REPEAT, 40, 0);
		
		loadAnimation("gfx/blaze_breathing.png", 3, 1, PlaySequence.REPEAT, 2, 0);
		
		TileType.init(this);
	}

}
