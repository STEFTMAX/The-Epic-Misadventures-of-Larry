package com.steftmax.temol.content.entity;

import com.steftmax.temol.content.entity.weapon.Bow;
import com.steftmax.temol.content.entity.weapon.Weapon;
import com.steftmax.temol.content.entity.weapon.WeaponWearer;
import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.SpriteGroup;
import com.steftmax.temol.graphics.sprite.animation.Animation;
import com.steftmax.temol.graphics.sprite.animation.PlaySequence;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;

public class Larry extends ControllableEntity implements WeaponWearer {

	private static final float sprintMultiplier = 1.45f;
	private static final Vector2 walkingSpeed = new Vector2(80, 0);
	private Vector2 lockingVector = new Vector2();
	boolean isPixelUpFrame = false;

	private SpriteGroup sprite = new SpriteGroup(0, 0);
	private Animation animation;
	
	public boolean looksLeft = false;
	private Weapon weapon;

	
	public Larry(TiledMap map, float x, float y, KeyboardInput ki,
			MouseInput mi, ResourceManager rm) {
		super(60, -1, 1, .5f, 10, mi, ki);

		// Just so there always is a texture in the drawingTexture pointer
		animation = new Animation(rm.getSpriteSheet("gfx/walking legs.png")
				.getFrames(), PlaySequence.REPEAT, 40);
		sprite.addSprite(animation);
		sprite.setPosition(position);
		
		weapon = new Bow(rm, mi, sprite);
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.setFlip(false, looksLeft);
		sprite.draw(batch);
		weapon.draw(batch);
	}

	@Override
	public void update(long delta) {

		if ((ki.isRightDown() && ki.isLeftDown() || (!ki.isRightDown() && !ki
				.isLeftDown()))) {

			animation.stop();
		} else {
			if (ki.isLeftDown()) {
				long usingDelta = delta;
				if (ki.isShiftDown()) {
					usingDelta *= sprintMultiplier;
				}
				position.subtract(walkingSpeed,
						TimeScaler.nanosToSecondsF(usingDelta));
				looksLeft = true;
				animation.update(usingDelta);

			}

			if (ki.isRightDown()) {
				long usingDelta = delta;
				if (ki.isShiftDown()) {
					usingDelta *= sprintMultiplier;
				}
				position.add(walkingSpeed,
						TimeScaler.nanosToSecondsF(usingDelta));
				looksLeft = false;
				animation.update(usingDelta);

			}
		}

		// TODO this should lock him at the head and be universal, to be tested
		// updateHitbox();
		lockingVector.set(position.x + animation.getWidth() / 2f, position.y + 27);

		final int frame = animation.lastFrame;
		isPixelUpFrame = (frame > 1 && frame < 11 || frame > 13 && frame < 24);
		sprite.setPosition(position);
		weapon.update(delta);
	}

	/**
	 * @return
	 */
	public Vector2 getLockingPosition() {
		return lockingVector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.content.entity.weapon.WeaponWearer#setToWeaponMountPoint
	 * (com.steftmax.temol.math.Vector2)
	 */
	@Override
	public Vector2 setToWeaponMountPoint(Vector2 vector) {
		vector.set(position);
		if (isPixelUpFrame)
			vector.add(0, 1);
		return vector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.content.entity.weapon.WeaponWearer#setToHead(com.steftmax
	 * .temol.math.Vector2)
	 */
	@Override
	public Vector2 setToHead(Vector2 vector) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.content.entity.weapon.WeaponWearer#isWalking()
	 */
	@Override
	public boolean isWalking() {
		// TODO Auto-generated method stub
		return false;
	}
}
