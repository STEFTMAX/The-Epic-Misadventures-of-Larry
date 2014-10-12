package com.sessionstraps.larrys_epic_misadventures.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sessionstraps.game_engine.entity.ControlableEntity;
import com.sessionstraps.game_engine.entity.LookDirection;
import com.sessionstraps.game_engine.input.EntityController;
import com.sessionstraps.game_engine.physics.Velocity;
import com.sessionstraps.game_engine.render.Renderable;
import com.sessionstraps.game_engine.resources.SpriteManager;
import com.sessionstraps.game_engine.sprite.animation.AnimationState;
import com.sessionstraps.larrys_epic_misadventures.Drawable;

public class Larry extends ControlableEntity implements Renderable, Drawable {

	public Larry(float x, float y, float dx, float dy, SpriteManager sm,
			EntityController ec) {
		super(x, y, dx, dy, sm, ec);

		walkingAnimationState = getAnim("larry_walking.png");
		standingAnimationState = getAnim("larry_breathing.png");
	}

	private static Velocity walkLeft = new Velocity(-50, 0),
			walkRight = new Velocity(50, 0);

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
		if (ec.leftDown()) {
			walkLeft.applyOnPosition(pos, delta);
			if (direction != LookDirection.LEFT) {

				direction = LookDirection.LEFT;
			}
			walkingAnimationState.update(delta);

		} else if (ec.rightDown()) {
			walkRight.applyOnPosition(pos, delta);

			if (direction != LookDirection.RIGHT) {

				direction = LookDirection.RIGHT;
			}
			walkingAnimationState.update(delta);
		}
		if (ec.noKeysDown()) {
			standingAnimationState.update(delta);

			walkingAnimationState.stop();
			standing = true;
		} else {
			standingAnimationState.stop();
			standing = false;
		}
	}

}
