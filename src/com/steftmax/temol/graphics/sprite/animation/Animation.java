package com.steftmax.temol.graphics.sprite.animation;

import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.render.Updatable;

public class Animation extends Sprite implements Updatable {

	// Essential animation variables
	private TextureRegion[] frames;

	private final PlaySequence sequence;
	private final float frameNanos;

	// Time variables

	public int lastFrame;
	public float lastNanos;

	public Animation(TextureRegion[] sprites, PlaySequence sequence, int fps) {
		super(sprites[0]);
		this.sequence = sequence;
		this.frames = sprites;
		this.frameNanos = 1f / fps;
	}

	@Override
	public void update(float deltaNanos) {

		lastNanos += deltaNanos;

		int framesPassed = (int) Math.floor(lastNanos / frameNanos);
		lastNanos -= frameNanos * framesPassed;
		if (framesPassed > 0) {
			lastFrame = sequence.getFrame(lastFrame, framesPassed,
					frames.length);
			setTo(frames[lastFrame]);
		}
	}

	public int getFrameAmount() {
		return frames.length;
	}

	/**
	 * 
	 */
	public void stop() {
		lastFrame = 0;
		lastNanos = 0;
		setTo(frames[lastFrame]);
	}

	/**
	 * @return
	 */
	public boolean isFirstFrame() {
		return lastFrame == 0;
	}
}