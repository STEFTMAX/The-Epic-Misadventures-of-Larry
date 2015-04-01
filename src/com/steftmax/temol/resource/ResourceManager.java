package com.steftmax.temol.resource;

import java.io.IOException;
import java.util.HashMap;

import com.steftmax.temol.audio.Music;
import com.steftmax.temol.audio.Sound;
import com.steftmax.temol.graphics.Texture;
import com.steftmax.temol.graphics.sprite.SpriteSheet;

/**
 * @author pieter3457
 *
 */
public abstract class ResourceManager {

	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	HashMap<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	HashMap<String, Music> musics = new HashMap<String, Music>();
	
	public abstract void load();

	public Texture getTexture(String path) {
		if (!textures.containsKey(path)) {
			loadTexture(path);
		}
		return textures.get(path);
	}

	public void loadTexture(String path) {

		Texture t = new Texture(path);
		t.load();

		textures.put(path, t);
	}

	public SpriteSheet getSpriteSheet(String path) {
		return sheets.get(path);
	}

	public void loadSpriteSheet(String path, int rows, int collumns) {

		final SpriteSheet s = new SpriteSheet(path, rows, collumns);

		sheets.put(path, s);
	}

	public void loadSpriteSheet(String path) {

		try {
			SpriteSheet s = null;
			s = new SpriteSheet(path);
			sheets.put(path, s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void unload() {
		// Textures
		for (Texture t : textures.values()) {
			t.dispose();
		}
		textures.clear();
//
//		// SpriteSheets
//		for (SpriteSheet s : sheets.values()) {
//			s.dispose();
//		}
//		sheets.clear();


		System.gc();
	}

}
