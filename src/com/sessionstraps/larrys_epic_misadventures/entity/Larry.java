package com.sessionstraps.larrys_epic_misadventures.entity;

import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.input.MouseInput;
import com.sessionstraps.game_engine.physics.Position;
import com.sessionstraps.game_engine.physics.Velocity;
import com.sessionstraps.game_engine.sprite.animation.Animation;
import com.sessionstraps.game_engine.sprite.animation.AnimationState;
import com.sessionstraps.larrys_epic_misadventures.LevelResources;
import com.sessionstraps.larrys_epic_misadventures.LevelResources.Animations;

public class Larry extends ControllableEntity implements LockableEntity {

	private static Velocity walkLeft = new Velocity(-100, 0),
			walkRight = new Velocity(100, 0);

	private AnimationState walkingAnimationState;
	private AnimationState standingAnimationState;

	private LookDirection direction = LookDirection.LEFT;

	public Larry(float x, float y, KeyboardInput ki, MouseInput mi, LevelResources lvlResources) {
		super(x, y, ki, mi);
		walkingAnimationState = new AnimationState((Animation) lvlResources.getResource(Animations.LARRY_WALKING));
		standingAnimationState = new AnimationState((Animation) lvlResources.getResource(Animations.LARRY_BREATHING));
	}

	@Override
	public void draw() {

		drawImage(direction == LookDirection.LEFT);
	}

	@Override
	public void update(long delta) {

		if ((ki.isRightDown() && ki.isLeftDown() || (!ki.isRightDown() && !ki
				.isLeftDown()))) {

			standingAnimationState.update(delta);
			drawingTexture = standingAnimationState.getCurrentTexture();
			walkingAnimationState.stop();
		} else {
			standingAnimationState.stop();
			if (ki.isLeftDown()) {

				walkLeft.applyOnPosition(pos, delta);
				direction = LookDirection.LEFT;
				walkingAnimationState.update(delta);
				drawingTexture = walkingAnimationState.getCurrentTexture();

			}

			if (ki.isRightDown()) {

				walkRight.applyOnPosition(pos, delta);
				direction = LookDirection.RIGHT;
				walkingAnimationState.update(delta);
				drawingTexture = walkingAnimationState.getCurrentTexture();
			}
		}
		//curvel.translate(0, TimeScaler.getTimeScaledFloat(150, delta));
		//curvel.applyOnPosition(pos, delta);
		
		// TODO Weapon.setPosition(drawingTexture.getMountingpoint());

	}

	@Override
	public Position getLockingPosition() {
		return pos;
	}
}
