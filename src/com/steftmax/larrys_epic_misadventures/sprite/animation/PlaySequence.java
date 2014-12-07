package com.steftmax.larrys_epic_misadventures.sprite.animation;

public interface PlaySequence {
	
	public static PlaySequence REPEAT = new PlaySequence() {
		
		@Override
		public int getFrame(AnimationState data, int framesPassed, int frames) {
			
			data.lastFrame += framesPassed;

			data.lastFrame %= frames;

			return data.lastFrame;
		}
	};

	public int getFrame(AnimationState data, int framesPassed, int frames);
}