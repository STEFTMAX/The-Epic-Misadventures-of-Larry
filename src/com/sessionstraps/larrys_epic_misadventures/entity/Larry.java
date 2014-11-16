package com.sessionstraps.larrys_epic_misadventures.entity;

import java.util.Set;

import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.input.MouseInput;
import com.sessionstraps.game_engine.physics.Position;
import com.sessionstraps.game_engine.physics.Velocity;
import com.sessionstraps.game_engine.resources.Loadable;
import com.sessionstraps.game_engine.resources.ResourceManager;
import com.sessionstraps.game_engine.sprite.SpriteSheet;
import com.sessionstraps.game_engine.sprite.animation.AnimationState;
import com.sessionstraps.game_engine.sprite.animation.PlaySequence;
import com.sessionstraps.game_engine.sprite.animation.SwingPlaySequence;

public class Larry extends ControllableEntity implements LockableEntity {

	private static Velocity walkLeft = new Velocity(-100, 0),
			walkRight = new Velocity(100, 0);

	private AnimationState walkingAnimationState;
	private AnimationState standingAnimationState;

	private LookDirection direction = LookDirection.LEFT;

	public Larry(float x, float y, KeyboardInput ki, MouseInput mi) {
		super(x, y, ki, mi);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sessionstraps.game_engine.level.LevelObject#getNeededResources(java
	 * .util.Set)
	 */
	@Override
	public void getNeededResources(Set<Loadable> toAddTo) {

		toAddTo.add(new SpriteSheet("larry_breathing.png", 3, 1,
				new SwingPlaySequence(true), 2, 0));
		toAddTo.add(new SpriteSheet("larry_walking.png", 13, 2,
				PlaySequence.REPEAT, 50, 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sessionstraps.game_engine.level.LevelObject#grabResources(com.
	 * sessionstraps.game_engine.resources.ResourceManager)
	 */
	@Override
	public void grabResources(ResourceManager rm) {
		standingAnimationState = getAnim("larry_breathing.png", rm);
		walkingAnimationState = getAnim("larry_walking.png", rm);
	}
}
