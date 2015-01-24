package com.steftmax.larrys_epic_misadventures.entity;


import com.steftmax.larrys_epic_misadventures.draw.GLGraphics;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.map.TiledMap;
import com.steftmax.larrys_epic_misadventures.math.Vector2;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources;
import com.steftmax.larrys_epic_misadventures.resource.LevelResources.Animations;
import com.steftmax.larrys_epic_misadventures.sprite.animation.Animation;
import com.steftmax.larrys_epic_misadventures.sprite.animation.AnimationState;
import com.steftmax.larrys_epic_misadventures.update.TimeScaler;

public class Larry extends ControllableEntity {

	private static final float sprintMultiplier = 1.45f;
	private static Vector2 walkingSpeed = new Vector2(80, 0);
	private Vector2 lockingVector = new Vector2();

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
		updateHitbox();
	}

	@Override
	public void draw() {
		
		if (looksLeft) {
			GLGraphics.drawTextureFromLeftBottomFlipped(drawingTexture, newPos);
		} else {
			GLGraphics.drawTextureFromLeftBottom(drawingTexture, newPos);
		}
	}

	@Override
	public void update(long delta) {

		lastPos.set(newPos);

		isOnGround = map.isOnGround(hitbox);
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
					long usingDelta = delta; 
					if (ki.isShiftDown()) {
						usingDelta *= sprintMultiplier;
					}
					newPos.subtract(walkingSpeed, TimeScaler.nanosToSecondsF(usingDelta));
					looksLeft = true;
					walkingAnimationState.update(usingDelta);
					drawingTexture = walkingAnimationState.getCurrentTexture();

				}

				if (ki.isRightDown()) {
					long usingDelta = delta; 
					if (ki.isShiftDown()) {
						usingDelta *= sprintMultiplier;
					}
					newPos.add(walkingSpeed, TimeScaler.nanosToSecondsF(usingDelta));
					looksLeft = false;
					walkingAnimationState.update(usingDelta);
					drawingTexture = walkingAnimationState.getCurrentTexture();
				}
			}
		}
		
		//this should lock him at the head
		updateHitbox();
		lockingVector.set(newPos.x + hitbox.width / 2f, newPos.y - 20);
	}

	/**
	 * @return
	 */
	public Vector2 getLockingPosition() {
		return lockingVector;
	}
}
