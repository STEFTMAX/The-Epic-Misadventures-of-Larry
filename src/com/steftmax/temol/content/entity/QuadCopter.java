package com.steftmax.temol.content.entity;

import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.math.Matrix2;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.resource.ResourceManager;

/**
 * @author pieter3457
 *
 */
public class QuadCopter extends Entity {

	/**
	 * @param map
	 * @param x
	 * @param y
	 * @param mass
	 * @param maxHP
	 */
	public QuadCopter(ResourceManager rm) {
		super(null, 0, 0, 0, 0);

		this.sprite = new Sprite(new TextureRegion(
				rm.getTexture("gfx/quadcopter.png")));
	}

	private Sprite sprite;

	private class Motor {
		private Vector2 force, rotatedForce = new Vector2();
		private Vector2 relativePosition,
				rotatedRelativePosition = new Vector2();

		public Motor(Vector2 force, Vector2 relativePosition) {
			this.force = force;
			rotatedForce.set(force);
			this.relativePosition = relativePosition;
			rotatedRelativePosition.set(relativePosition);
		}

		public void transform(Matrix2 transform) {
			Vector2.multiply(transform, force, rotatedForce);
			Vector2.multiply(transform, relativePosition,
					rotatedRelativePosition);
		}

		/**
		 * @param quadCopter
		 */
		public void applyForce(QuadCopter quadCopter) {
			quadCopter.addForce(rotatedRelativePosition, rotatedForce);
		}
	}

	Matrix2 transform = new Matrix2();

	final private Motor leftMotor = new Motor(new Vector2(0,8), new Vector2(
			-.24f, .1f)), rightMotor = new Motor(new Vector2(0, 11),
			new Vector2(.24f, .1f));

	@Override
	public void update(long delta) {
		transform.setRotate(rotation);
		leftMotor.transform(transform);
		rightMotor.transform(transform);
		leftMotor.applyForce(this);
		rightMotor.applyForce(this);
		System.out.println("Torque" + torque);
		System.out.println("Force" + force);
		sprite.set(position.x, position.y);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics
	 * .SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(sprite);
	}
}
