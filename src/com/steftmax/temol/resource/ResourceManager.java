package com.steftmax.temol.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import com.steftmax.temol.audio.Music;
import com.steftmax.temol.audio.Sound;
import com.steftmax.temol.graphics.Texture;
import com.steftmax.temol.graphics.TextureAtlas;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.SpriteSheet;

/**
 * @author pieter3457
 *
 */
public abstract class ResourceManager {

	public HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public HashSet<TextureAtlas> atlasses = new HashSet<TextureAtlas>();
	public HashMap<String, TextureRegion> regions = new HashMap<String, TextureRegion>();
	public HashMap<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();
	public HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	public HashMap<String, Music> musics = new HashMap<String, Music>();

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

	public void loadToTextureAtlas(String path) {
		for (TextureAtlas atlas : atlasses) {
			TextureRegion tr = atlas.add(path);
			if (tr != null) {
				regions.put(path, tr);
				return;
			}
			
		}
		TextureAtlas ta = new TextureAtlas(2048, 2048);
		atlasses.add(ta);
		regions.put(path, ta.add(path));
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
		// // SpriteSheets
		// for (SpriteSheet s : sheets.values()) {
		// s.dispose();
		// }
		// sheets.clear();

		System.gc();
	}

}
