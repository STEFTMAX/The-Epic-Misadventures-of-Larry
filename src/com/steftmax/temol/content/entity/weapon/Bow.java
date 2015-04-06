package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.content.Level;
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

	private float rotation;
	private Larry larry;
	private ResourceManager rm;
	private Level level;

	// private static final int XFLIPPEDOFFSET = -15, FINALFRAME = 6;

	public Bow(ResourceManager gameResources, MouseInput mi, Larry larry,
			SpriteGroup group, Level level) {

		super(gameResources, mi);
		this.level = level;
		this.group = group;
		this.rm = gameResources;

		TextureRegion[] frames = gameResources
				.getSpriteSheet("gfx/bow top.png").obtainFrames(1, 7);
		neutral = new Sprite(gameResources.getSpriteSheet("gfx/bow top.png")
				.obtainFrames(0, 1)[0]);

		animation = new Animation(frames, PlaySequence.HOLDLAST, 10);
		animation.setOrigin(13, 21);
		group.addSprite(neutral);
		this.larry = larry;
	}

	private boolean isAiming;

	public void update(float delta) {
		// if (isAiming && !mi.primaryDown()) {
		// Arrow arrow = new Arrow(larry.position.x, larry.position.y, 1, 1,
		// rm);
		// level.addLevelEntity(arrow);
		// }
		isAiming = mi.primaryDown();
		if (isAiming) {
			if (animation.lastFrame == 0)
				group.replaceSprite(neutral, animation);
			animation.update(delta);

			float pi = (float) Math.PI;

			final Vector2 pos = mi.getMousePosition();

			float rotation = (float) Math.atan2(pos.y
					- (Settings.getHeight() / 2), pos.x
					- (Settings.getWidth() / 2));

			if (!larry.isWalking()) {
				larry.looksLeft = pos.x < Settings.getWidth() / 2f;
			}

			if (larry.looksLeft) {

				rotation -= pi;
				if (rotation < -pi) {
					rotation += 2 * pi;
				}
				if (rotation > .75f) {
					rotation = .75f;
				}
				if (rotation < -.75f) {
					rotation = -.75f;
				}

				// the rotation range is longer here but that doesnt matter
				this.rotation = rotation + pi;

			} else {

				if (rotation > .75f) {
					rotation = .75f;
				}
				if (rotation < -.75f) {
					rotation = -.75f;
				}
				this.rotation = rotation;

			}

			animation.setRotation(rotation);
			animation.setPosition(0, larry.isPixelUpFrame() ? 1 : 0);
		} else {
			animation.stop();
			neutral.setPosition(0, larry.isPixelUpFrame() ? 1 : 0);
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
		return !mi.primaryDown();
	}
}
