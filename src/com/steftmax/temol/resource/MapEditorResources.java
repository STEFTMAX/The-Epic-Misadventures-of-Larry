package com.steftmax.temol.resource;

/**
 * @author pieter3457
 *
 */
public class MapEditorResources extends ResourceManager{

	/* (non-Javadoc)
	 * @see com.steftmax.temol.resource.ResourceManager#load()
	 */
	@Override
	public void load() {
		loadSpriteSheet(path, rows, collumns);
	}

}
