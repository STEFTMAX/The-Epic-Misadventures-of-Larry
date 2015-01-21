package com.steftmax.larrys_epic_misadventures.entity;

import com.steftmax.larrys_epic_misadventures.draw.Drawer.DrawPriority;
import com.steftmax.larrys_epic_misadventures.draw.GLGraphics;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.math.Vector2F;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources.Animations;
import com.steftmax.larrys_epic_misadventures.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.sprite.animation.AnimationState;
import com.steftmax.larrys_epic_misadventures.update.TimeScaler;

public class Larry extends ControllableEntity {

	private static Vector2F walkingSpeed = new Vector2F(100, 0);
	private Vector2F lockingVector = new Vector2F();

	private AnimationState walkingAnimationState, standingAnimationState;

	private boolean looksLeft = false;

	
	// Entities should only keep one animation state object for all animations they hold.
	public Larry(TiledMap map, float x, float y, KeyboardInput ki,
			MouseInput mi, LevelResources lvlResources) {
		super(map, x, y, 50, 10, ki, mi);
		walkingAnimationState = new AnimationState(
				(Animation) lvlResources.getResource(Animations.LARRY_WALKING));
		standingAnimationState = new AnimationState(
				(Animation) lvlResources
						.getResource(Animations.LARRY_BREATHING));
		//Just so there always is a texture in the drawingTexture pointer
		drawingTexture = standingAnimationState.getCurrentTexture();
	}

	@Override
	public void draw(final DrawPriority dp) {
		if (dp != DrawPriority.FRONT)
			return;
		if (looksLeft) {
			GLGraphics.drawTextureFromLeftBottomFlipped(drawingTexture, newPos);
		} else {
			GLGraphics.drawTextureFromLeftBottom(drawingTexture, newPos);
		}
	}

	@Override
	public void update(long delta) {

		lastPos.set(newPos);

		isOnGround = map.isOnGround(getHitbox());
		// move this to entity part
		if (!isOnGround) {

			velocity.add(0f, 100f, TimeScaler.nanosToSecondsF(delta));
			newPos.add(velocity, TimeScaler.nanosToSecondsF(delta));
			
		} else {
			velocity.set(0, 0);
			if ((ki.isRightDown() && ki.isLeftDown() || (!ki.isRightDown() && !ki
					.isLeftDown()))) {
				

				standingAnimationState.update(delta);
				drawingTexture = standingAnimationState.getCurrentTexture();
				walkingAnimationState.stop();
			} else {
				standingAnimationState.stop();
				if (ki.isLeftDown()) {

					newPos.substract(walkingSpeed, TimeScaler.nanosToSecondsF(delta));
					looksLeft = true;
					walkingAnimationState.update(delta);
					drawingTexture = walkingAnimationState.getCurrentTexture();

				}

				if (ki.isRightDown()) {

					newPos.add(walkingSpeed, TimeScaler.nanosToSecondsF(delta));
					looksLeft = false;
					walkingAnimationState.update(delta);
					drawingTexture = walkingAnimationState.getCurrentTexture();
				}
			}
		}
		
		//this should lock him at the head
		lockingVector.set(newPos.x + getHitbox().width / 2f, newPos.y - 20);

	}

	/**
	 * @return
	 */
	public Vector2F getLockingPosition() {
		return lockingVector;
	}
}
