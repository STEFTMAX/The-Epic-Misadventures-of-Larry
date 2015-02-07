package com.steftmax.temol.graphics.sprite.animation;

public class SwingPlaySequence implements PlaySequence {

	private boolean repeatOuter;
	private boolean goingUp = true;

	public SwingPlaySequence(boolean repeatOuter) {
		this.repeatOuter = repeatOuter;
	}

	@Override
	public int getFrame(AnimationState data, int framesPassed, int frames) {

		while (framesPassed > 0) {
			data.lastFrame = getNextFrame(data.lastFrame, frames);
			framesPassed--;
		}
		
		return data.lastFrame;

	}

	private int getNextFrame(int lastFrame, int frames) {
		if (repeatOuter) {

			if (goingUp) {
				if (lastFrame + 1 > frames - 1) {
					goingUp = false;
					return frames - 1;
				} else {
					return lastFrame + 1;
				}

			} else {
				if (lastFrame - 1 < 0) {
					goingUp = true;
					return 0;
				} else {
					return lastFrame - 1;
				}

			}
		} else {

			if (goingUp) {
				if (lastFrame + 1 > frames - 1) {
					goingUp = false;
					return lastFrame - 1;
				} else {
					return lastFrame + 1;
				}

			} else {
				if (lastFrame - 1 < 0) {
					goingUp = true;
					return 1;
				} else {
					return lastFrame - 1;
				}

			}
		}
	}
}
