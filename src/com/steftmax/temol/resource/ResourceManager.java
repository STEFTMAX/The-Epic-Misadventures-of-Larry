package com.steftmax.temol.resource;

import java.util.HashMap;

import com.steftmax.temol.graphics.sprite.SpriteSheet;
import com.steftmax.temol.graphics.sprite.Texture;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.graphics.sprite.animation.Animation;
import com.steftmax.temol.graphics.sprite.animation.PlaySequence;

/**
 * @author pieter3457
 *
 */
public abstract class ResourceManager{

	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	HashMap<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();
	HashMap<String, Animation> animations = new HashMap<String, Animation>();

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

	public Animation getAnimation(String path) {
		return animations.get(path);
	}

	public void loadAnimation(String path, int rows, int collumns,
			PlaySequence sequence, int fps, int skipLastFrames) {
		
		final SpriteSheet sheet = new SpriteSheet(path, rows, collumns);
		
		final TextureRegion[] sprites = sheet.getFrames();
		
		final TextureRegion[] frames = new TextureRegion[sprites.length - skipLastFrames];
		
		for (int i = 0; i< frames.length ; i++) {
			frames[i] = sprites[i];
		}
		
		animations.put(path, new Animation(frames, sequence, fps));
	}
	
	public void unload() {
		// Textures
		for (Texture t : textures.values()) {
			t.dispose();
		}
		textures.clear();

		// SpriteSheets
		for (SpriteSheet s : sheets.values()) {
			s.dispose();
		}
		sheets.clear();

		// Animations
		for (Animation a : animations.values()) {
			a.dispose();
		}
		animations.clear();
		
		System.gc();
	}

}
