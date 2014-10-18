package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sessionstraps.game_engine.entity.LockableEntity;
import com.sessionstraps.game_engine.entity.LookDirection;
import com.sessionstraps.game_engine.entity.player.ControlableEntity;
import com.sessionstraps.game_engine.input.EntityController;
import com.sessionstraps.game_engine.physics.Position;
import com.sessionstraps.game_engine.physics.Velocity;
import com.sessionstraps.game_engine.render.Drawable;
import com.sessionstraps.game_engine.render.Renderable;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.game_engine.sprite.animation.AnimationState;

public class Larry extends ControlableEntity implements Renderable, Drawable,
		LockableEntity {

	public Larry(float x, float y, SpriteManager sm, EntityController ec) {
		super(x, y, sm, ec);

		walkingAnimationState = getAnim("larry_walking.png");
		standingAnimationState = getAnim("larry_breathing.png");

		initEntity(10, 60);
	}

	private static Velocity walkLeft = new Velocity(-100, 0),
			walkRight = new Velocity(100, 0);

	private AnimationState walkingAnimationState;
	private AnimationState standingAnimationState;

	private boolean standing;
	private LookDirection direction = LookDirection.LEFT;

	@Override
	public void draw(Graphics2D g) {
		BufferedImage img;
		if (standing) {
			img = (BufferedImage) standingAnimationState.getCurrentImage();
		} else {
			img = (BufferedImage) walkingAnimationState.getCurrentImage();

		}
		if (direction == LookDirection.LEFT) {

			// inverted Drawing
			g.drawImage(img, pos.getRoundedX() + img.getWidth() / 2,
					pos.getRoundedY() - img.getHeight(), pos.getRoundedX()
							- img.getWidth() / 2, pos.getRoundedY(), 0, 0,
					img.getWidth(), img.getHeight(), null);

		} else {
			g.drawImage(img, pos.getRoundedX() - img.getWidth(null) / 2,
					pos.getRoundedY() - img.getHeight(null), null);
		}

	}

	@Override
	public void render(long delta) {
		if (ec.isKeyDown(KeyEvent.VK_LEFT)) {
			walkLeft.applyOnPosition(pos, delta);
			if (direction != LookDirection.LEFT) {

				direction = LookDirection.LEFT;
			}
			walkingAnimationState.update(delta);

		} else if (ec.isKeyDown(KeyEvent.VK_RIGHT)) {
			walkRight.applyOnPosition(pos, delta);

			if (direction != LookDirection.RIGHT) {

				direction = LookDirection.RIGHT;
			}
			walkingAnimationState.update(delta);
		} else {
			standingAnimationState.update(delta);

			walkingAnimationState.stop();
			standing = true;
		}
		standingAnimationState.stop();
		standing = false;

		// TODO change to state thingy and current Image and shit
	}

	@Override
	public Position getLockingPosition() {
		return pos;
	}
}
