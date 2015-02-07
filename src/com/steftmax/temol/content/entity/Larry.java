package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.animation.AnimationState;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;

public class Larry extends ControllableEntity {

	private static final float sprintMultiplier = 1.45f;
	private static Vector2 walkingSpeed = new Vector2(80, 0);
	private Vector2 lockingVector = new Vector2();

	private AnimationState walkingAnimationState, standingAnimationState;

	private boolean looksLeft = false;

	// Entities should only keep one animation state object for all animations
	// they hold.
	public Larry(TiledMap map, float x, float y, KeyboardInput ki,
			MouseInput mi, ResourceManager rm) {
		super(map, x, y, 50, 10, ki, mi);

		walkingAnimationState = new AnimationState(
				rm.getAnimation("gfx/larry_walking.png"));
		standingAnimationState = new AnimationState(
				rm.getAnimation("gfx/larry_breathing.png"));
		// Just so there always is a texture in the drawingTexture pointer
		sprite.set(standingAnimationState.getCurrent(), newPos);
		updateHitbox();
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.flipY = looksLeft;
		batch.draw(sprite);
		// if (looksLeft) {
		// GLGraphics.drawTextureFromLeftBottomFlipped(drawingTexture, newPos);
		// } else {
		// GLGraphics.drawTextureFromLeftBottom(drawingTexture, newPos);
		// }
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
				sprite.set(standingAnimationState.getCurrent(), newPos);
				walkingAnimationState.stop();
			} else {
				standingAnimationState.stop();
				if (ki.isLeftDown()) {
					long usingDelta = delta;
					if (ki.isShiftDown()) {
						usingDelta *= sprintMultiplier;
					}
					newPos.subtract(walkingSpeed,
							TimeScaler.nanosToSecondsF(usingDelta));
					looksLeft = true;
					walkingAnimationState.update(usingDelta);
					sprite.set(walkingAnimationState.getCurrent(), newPos);

				}

				if (ki.isRightDown()) {
					long usingDelta = delta;
					if (ki.isShiftDown()) {
						usingDelta *= sprintMultiplier;
					}
					newPos.add(walkingSpeed,
							TimeScaler.nanosToSecondsF(usingDelta));
					looksLeft = false;
					walkingAnimationState.update(usingDelta);
					sprite.set(walkingAnimationState.getCurrent(), newPos);
				}
			}
		}

		//TODO this should lock him at the head and be universal, to be tested 
		updateHitbox();
		lockingVector.set(newPos.x + hitbox.width / 2f, newPos.y + 27);
	}

	/**
	 * @return
	 */
	public Vector2 getLockingPosition() {
		return lockingVector;
	}
}
