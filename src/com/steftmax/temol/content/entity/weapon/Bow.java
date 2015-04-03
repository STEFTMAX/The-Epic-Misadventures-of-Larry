package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.SpriteGroup;
import com.steftmax.temol.graphics.sprite.animation.Animation;
import com.steftmax.temol.graphics.sprite.animation.PlaySequence;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;
import com.steftmax.temol.resource.Settings;

/**
 * @author pieter3457
 *
 */
public class Bow extends Weapon {

	Animation animation;
	boolean playing;
	private SpriteGroup group;
	private Sprite neutral;

	// private Larry larry;
	// private static final int XFLIPPEDOFFSET = -15, FINALFRAME = 6;

	public Bow(ResourceManager gameResources, MouseInput mi, SpriteGroup group) {
		super(gameResources, mi);
		this.group = group;

		TextureRegion[] frames = gameResources
				.getSpriteSheet("gfx/bow top.png").obtainFrames(1, 7);
		neutral = new Sprite(gameResources.getSpriteSheet("gfx/bow top.png")
				.obtainFrames(0, 1)[0]);

		animation = new Animation(frames, PlaySequence.HOLDLAST, 10);
		animation.setOrigin(13, 21);
		group.addSprite(neutral);
	}

	private boolean isAiming;

	public void update(long delta) {
		isAiming = mi.primaryDown();
		if (isAiming) {
			if (animation.lastFrame == 0)
				group.replaceSprite(neutral, animation);
			animation.update(delta);

			Vector2 pos = mi.getMousePosition();
			float rotation = (float) Math.atan2(pos.y
					- (Settings.getHeight() / 2), pos.x
					- (Settings.getWidth() / 2));
			if (rotation > .75f) {
				rotation = .75f;
			}
			if (rotation < -.75f) {
				rotation = -.75f;
			}
			animation.setRotation(rotation);
		} else {
			animation.stop();
			group.replaceSprite(animation, neutral);
		}
	}

	public void draw(SpriteBatch batch) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.content.entity.weapon.Weapon#drawsFace()
	 */
	@Override
	public boolean drawsFace() {
		return mi.primaryDown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.content.entity.weapon.Weapon#allowSprinting()
	 */
	@Override
	public boolean allowSprinting() {
		return mi.primaryDown();
	}
}
