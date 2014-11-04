package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Set;

import com.sessionstraps.game_engine.entity.LockableEntity;
import com.sessionstraps.game_engine.entity.LookDirection;
import com.sessionstraps.game_engine.entity.player.ControlableEntity;
import com.sessionstraps.game_engine.input.KeyboardInput;
import com.sessionstraps.game_engine.physics.Position;
import com.sessionstraps.game_engine.physics.Velocity;
import com.sessionstraps.game_engine.render.Drawable;
import com.sessionstraps.game_engine.render.Renderable;
import com.sessionstraps.game_engine.resources.ResourceManager;
import com.sessionstraps.game_engine.sprite.animation.AnimationState;

public class Larry extends ControlableEntity implements Renderable, Drawable,
		LockableEntity {

	private static Velocity walkLeft = new Velocity(-100, 0),
			walkRight = new Velocity(100, 0);

	private AnimationState walkingAnimationState;
	private AnimationState standingAnimationState;

	private LookDirection direction = LookDirection.LEFT;

	public Larry(float x, float y, ResourceManager rm, KeyboardInput ec) {
		super(x, y, rm, ec);

		walkingAnimationState = getAnim("larry_walking.png");
		standingAnimationState = getAnim("larry_breathing.png");

		initEntity(10, 60);
	}

	@Override
	public void draw(Graphics2D g) {

		drawImage(g, direction == LookDirection.LEFT);
	}

	@Override
	public void render(long delta) {
		
		if ((ec.isKeyDown(KeyEvent.VK_RIGHT) && ec.isKeyDown(KeyEvent.VK_LEFT) || (!ec
				.isKeyDown(KeyEvent.VK_RIGHT) && !ec
				.isKeyDown(KeyEvent.VK_LEFT)))) {

			standingAnimationState.update(delta);
			this.drawingImage = standingAnimationState.getCurrentImage();
			walkingAnimationState.stop();
		} else {
			if (ec.isKeyDown(KeyEvent.VK_LEFT)) {

				walkLeft.applyOnPosition(pos, delta);
				direction = LookDirection.LEFT;
				walkingAnimationState.update(delta);
				drawingImage = (BufferedImage) walkingAnimationState
						.getCurrentImage();

			}

			if (ec.isKeyDown(KeyEvent.VK_RIGHT)) {

				walkRight.applyOnPosition(pos, delta);
				direction = LookDirection.RIGHT;
				walkingAnimationState.update(delta);
				drawingImage = (BufferedImage) walkingAnimationState
						.getCurrentImage();
			}
		}

		// TODO change to state thingy and current Image and shit
		
	}

	@Override
	public Position getLockingPosition() {
		return pos;
	}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.level.LevelObject#getNeededResourses()
	 */
	@Override
	public void getNeededResourses(Set<String> toPutTo) {
		
		toPutTo.add("larry_walking.png");
		toPutTo.add("larry_breathing.png");
		
	}
}
