package com.steftmax.larrys_epic_misadventures.resource;

import java.util.HashMap;

import com.steftmax.larrys_epic_misadventures.graphics.sprite.SpriteSheet;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.TextureRegion;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.animation.PlaySequence;

/**
 * @author pieter3457
 *
 */
public abstract class ResourceManager implements Loadable {

	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	HashMap<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();
	HashMap<String, Animation> animations = new HashMap<String, Animation>();

	@Override
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
		s.load();

		sheets.put(path, s);
	}

	public Animation getAnimation(String path) {
		return animations.get(path);
	}

	public void loadAnimation(String path, int rows, int collumns,
			PlaySequence sequence, int fps, int skipLastFrames) {
		
		final SpriteSheet sheet = new SpriteSheet(path, rows, collumns);
		sheet.load();
		
		final TextureRegion[] sprites = sheet.getFrames();
		
		final TextureRegion[] frames = new TextureRegion[sprites.length - skipLastFrames];
		
		for (int i = 0; i< frames.length ; i++) {
			frames[i] = sprites[i];
		}
		
		animations.put(path, new Animation(frames, sequence, fps));
	}

	@Override
	public void unload() {
		// Textures
		for (Texture t : textures.values()) {
			t.unload();
		}
		textures.clear();

		// SpriteSheets
		for (SpriteSheet s : sheets.values()) {
			s.unload();
		}
		sheets.clear();

		// Animations
		for (Animation a : animations.values()) {
			a.unload();
		}
		animations.clear();
		
		System.gc();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.resource.Loadable#isLoaded()
	 */
	@Override
	public boolean isLoaded() {
		return textures.size() > 0;
	}

}
