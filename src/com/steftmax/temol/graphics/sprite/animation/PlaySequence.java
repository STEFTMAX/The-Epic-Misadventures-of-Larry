package com.steftmax.temol.graphics.sprite.animation;

public interface PlaySequence {

	public static PlaySequence REPEAT = new PlaySequence() {

		@Override
		public int getFrame(int lastFrame, int framesPassed, int frames) {

			lastFrame += framesPassed;

			lastFrame %= frames;

			return lastFrame;
		}
	};

	public static PlaySequence HOLDLAST = new PlaySequence() {

		@Override
		public int getFrame(int lastFrame, int framesPassed, int frames) {

			lastFrame += framesPassed;

			if (lastFrame >= frames) {
				lastFrame = frames - 1;
			}

			return lastFrame;
		}
	};

	public int getFrame(int lastFrame, int framesPassed, int frames);
}