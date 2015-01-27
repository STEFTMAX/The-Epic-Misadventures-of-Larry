package com.steftmax.larrys_epic_misadventures.sprite.animation;

import com.steftmax.larrys_epic_misadventures.sprite.TextureRegion;

public class Animation {

	// Essential animation variables
	private TextureRegion[] frames;

	private final PlaySequence sequence;
	private final int frameNanos;

	public Animation(TextureRegion[] sprites, PlaySequence sequence, int fps) {
		this.sequence = sequence;
		this.frames = sprites;
		this.frameNanos = 1000000000 / fps;
	}

	public void update(long deltaNanos, AnimationState data) {

		data.lastNanos += deltaNanos;

		int framesPassed = (int) Math.floor(data.lastNanos / frameNanos);
		data.lastNanos -= frameNanos * framesPassed;
		if (framesPassed > 0) {
			data.lastFrame = sequence.getFrame(data, framesPassed,
					frames.length);
		}
	}
	
	public void unload() {
		for (TextureRegion s: frames) {
			s.unload();
		}
	}

	public TextureRegion getCurrent(AnimationState data) {
		return frames[data.lastFrame];
	}

	public int getFrameAmount() {
		return frames.length;
	}

	public int getWidth(AnimationState data) {
		return frames[data.lastFrame].width;
	}

	public int getHeight(AnimationState data) {
		return frames[data.lastFrame].height;
	}
}