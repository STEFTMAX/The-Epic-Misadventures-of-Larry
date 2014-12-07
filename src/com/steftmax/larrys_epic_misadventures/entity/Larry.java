package com.steftmax.larrys_epic_misadventures.entity;

import com.steftmax.larrys_epic_misadventures.draw.GLGraphics;
import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.physics.Velocity;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources.Animations;
import com.steftmax.larrys_epic_misadventures.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.sprite.animation.AnimationState;

public class Larry extends ControllableEntity {

	private static Velocity walkLeft = new Velocity(-100, 0),
			walkRight = new Velocity(100, 0);

	private AnimationState walkingAnimationState;
	private AnimationState standingAnimationState;

	private boolean looksLeft = false;

	public Larry(float x, float y, KeyboardInput ki, MouseInput mi, LevelResources lvlResources) {
		super(x, y, ki, mi);
		walkingAnimationState = new AnimationState((Animation) lvlResources.getResource(Animations.LARRY_WALKING));
		standingAnimationState = new AnimationState((Animation) lvlResources.getResource(Animations.LARRY_BREATHING));
	}

	@Override
	public void draw(final DrawPriority dp) {
		if (dp != DrawPriority.FRONT) return;
		if (looksLeft) {
			GLGraphics.drawTextureFromLeftBottomFlipped(drawingTexture, newPos);
		} else {
			GLGraphics.drawTextureFromLeftBottom(drawingTexture, newPos);
		}
	}

	@Override
	public void update(long delta) {
		lastPos.set(newPos);
		
		if ((ki.isRightDown() && ki.isLeftDown() || (!ki.isRightDown() && !ki
				.isLeftDown()))) {

			standingAnimationState.update(delta);
			drawingTexture = standingAnimationState.getCurrentTexture();
			walkingAnimationState.stop();
		} else {
			standingAnimationState.stop();
			if (ki.isLeftDown()) {

				walkLeft.applyOnPosition(newPos, delta);
				looksLeft = true;
				walkingAnimationState.update(delta);
				drawingTexture = walkingAnimationState.getCurrentTexture();

			}

			if (ki.isRightDown()) {

				walkRight.applyOnPosition(newPos, delta);
				looksLeft = false;
				walkingAnimationState.update(delta);
				drawingTexture = walkingAnimationState.getCurrentTexture();
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.physics.Collidable#setPreviousPosition()
	 */
	@Override
	public void setPreviousPosition() {
		newPos.set(lastPos);
	}

}
