package com.steftmax.larrys_epic_misadventures.graphics.sprite.animation;

import com.steftmax.larrys_epic_misadventures.graphics.sprite.TextureRegion;


public class AnimationState {

	public int lastFrame;
	public int lastNanos;
	private Animation anim;

	public AnimationState(Animation anim) {
		this.anim = anim;

	}

	public void stop() {
		lastFrame = 0;
		lastNanos = 0;
	}

	public TextureRegion getCurrent() {
		return anim.getCurrent(this);
	}

	public void update(long deltaNanos) {
		anim.update(deltaNanos, this);
	}
	
	public int getWidht() {
		return anim.getWidth(this);
	}
	
	public int getHeight() {
		return anim.getWidth(this);
	}

}
