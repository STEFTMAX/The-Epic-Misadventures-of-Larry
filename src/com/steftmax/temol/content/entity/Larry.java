package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.entity.weapon.Bow;
import com.steftmax.temol.content.entity.weapon.Weapon;
import com.steftmax.temol.content.entity.weapon.WeaponWearer;
import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.animation.AnimationState;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;

public class Larry extends ControllableEntity implements WeaponWearer{

	private static final float sprintMultiplier = 1.45f;
	private static final Vector2 walkingSpeed = new Vector2(80, 0);
	private Vector2 lockingVector = new Vector2();
	private Vector2 weaponPoint = new Vector2();

	private AnimationState walkingAnimationState, standingAnimationState;

	public boolean looksLeft = false;
	private Weapon weapon;

	// Entities should only keep one animation state object for all animations
	// they hold.
	public Larry(TiledMap map, float x, float y, KeyboardInput ki,
			MouseInput mi, ResourceManager rm) {
		super(60, -1, 1, .5f, 10, mi, ki);

		walkingAnimationState = new AnimationState(
				rm.getAnimation("gfx/walking legs.png"));
		standingAnimationState = new AnimationState(
				rm.getAnimation("gfx/larry_breathing.png"));
		// Just so there always is a texture in the drawingTexture pointer
		sprite= new Sprite(standingAnimationState.getCurrent());
		sprite.set(position);
		sprite.centerOrigin();
		weapon = new Bow(rm, mi, this);
		updateHitbox();
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.flipY = looksLeft;
		sprite.centerOrigin();
		batch.draw(sprite);
		sprite.rotate(.001f);
		weapon.draw(batch);
		// if (looksLeft) {
		// GLGraphics.drawTextureFromLeftBottomFlipped(drawingTexture, newPos);
		// } else {
		// GLGraphics.drawTextureFromLeftBottom(drawingTexture, newPos);
		// }
	}

	@Override
	public void update(long delta) {

		if ((ki.isRightDown() && ki.isLeftDown() || (!ki.isRightDown() && !ki
				.isLeftDown()))) {

			standingAnimationState.update(delta);
			sprite.set(standingAnimationState.getCurrent(), position);
			walkingAnimationState.stop();
		} else {
			standingAnimationState.stop();
			if (ki.isLeftDown()) {
				long usingDelta = delta;
				if (ki.isShiftDown()) {
					usingDelta *= sprintMultiplier;
				}
				position.subtract(walkingSpeed,
						TimeScaler.nanosToSecondsF(usingDelta));
				looksLeft = true;
				walkingAnimationState.update(usingDelta);
				sprite.set(walkingAnimationState.getCurrent(), position);

			}

			if (ki.isRightDown()) {
				long usingDelta = delta;
				if (ki.isShiftDown()) {
					usingDelta *= sprintMultiplier;
				}
				position.add(walkingSpeed,
						TimeScaler.nanosToSecondsF(usingDelta));
				looksLeft = false;
				walkingAnimationState.update(usingDelta);
				sprite.set(walkingAnimationState.getCurrent(), position);

			}
		}

		// TODO this should lock him at the head and be universal, to be tested
		// updateHitbox();
		 lockingVector.set(position.x + hitbox.width / 2f, position.y + 27);
		 weaponPoint.set(position.x, position.y);
		 int frame = walkingAnimationState.getFrameNumber();
		 if (frame >3 && frame < 10 || frame > 16 && frame < 23) {
			 weaponPoint.add(0, 1);
		 }
		 weapon.update(delta);
	}

	/**
	 * @return
	 */
	public Vector2 getLockingPosition() {
		return lockingVector;
	}

	/* (non-Javadoc)
	 * @see com.steftmax.temol.content.entity.weapon.WeaponWearer#getWeaponMountPoint()
	 */
	@Override
	public Vector2 getWeaponMountPoint() {
		return weaponPoint;
	}
}
