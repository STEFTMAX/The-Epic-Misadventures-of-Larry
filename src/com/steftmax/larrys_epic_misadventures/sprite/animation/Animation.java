package com.steftmax.larrys_epic_misadventures.sprite.animation;

import com.steftmax.larrys_epic_misadventures.resource.Loadable;
import com.steftmax.larrys_epic_misadventures.resource.loader.SpriteSheetLoader;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

import java.lang.Math;

public class Animation implements Loadable{
	
	//Essential animation variables
	private Texture[] frames;
	
	private final PlaySequence sequence;
	private final int frameNanos;

	//These fields may be unloaded after load
	private String path;
	private int x, y, skipLast;

	@Deprecated
	public Animation(Texture[] sprites, PlaySequence sequence, int fps) {
		this.sequence = sequence;
		this.frames = sprites;
		this.frameNanos = 1000000000 / fps;
	}
	public Animation(String path, int x, int y, int skipLast, PlaySequence sequence, int fps) {
		this.sequence = sequence;
		this.frameNanos = 1000000000 / fps;
		this.path = path; 
		this.x = x;
		this.y = y;
		this.skipLast = skipLast;
	}
	
	public void update(long deltaNanos, AnimationState data) {
		
		data.lastNanos += deltaNanos;
		
		int framesPassed = (int) Math.floor(data.lastNanos / frameNanos);
		data.lastNanos -= frameNanos * framesPassed;
		if (framesPassed > 0) {
			data.lastFrame = sequence.getFrame(data, framesPassed, frames.length);
		}
	}

	public Texture getCurrentTexture(AnimationState data) {
		return frames[data.lastFrame];
	}
     public int getFrameAmount() {
          return frames.length;
     }
	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.resources.Loadable#load()
	 */
	@Override
	public void load() {
		this.frames = SpriteSheetLoader.loadTextures(path, x, y, skipLast);
	}
	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.resources.Loadable#unload()
	 */
	@Override
	public void unload() {
		for (Texture t : frames) {
			t.unload();
		}
		frames = null;
	}
	
	public int getWidth(AnimationState data) {
		return frames[data.lastFrame].width;
	}
	
	public int getHeight(AnimationState data) {
		return frames[data.lastFrame].height;
	}
}