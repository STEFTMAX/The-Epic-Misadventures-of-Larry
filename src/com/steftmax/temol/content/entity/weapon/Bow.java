package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;
import com.steftmax.temol.resource.Settings;

/**
 * @author pieter3457
 *
 */
public class Bow extends Weapon {

	Sprite frame = new Sprite();
	boolean playing;
	private TextureRegion[] frames;
	private Larry larry;
	private static final int XFLIPPEDOFFSET = -15, FINALFRAME = 6;

	public Bow(ResourceManager gameResources, MouseInput mi, Larry larry) {
		super(gameResources, mi);
		this.larry = larry;
		frames = gameResources.getSpriteSheet("gfx/bow top.png").getFrames();
		frame.setToTextureRegion(frames[0]);
		frame.setOrigin(13, 21);
	}

	int frameN = 0;
	long currentTime;
	long stepTime = 100000000;

	public void update(long delta) {
		if (mi.primaryDown()) {
			if (frameN < FINALFRAME) {
				currentTime += delta;
				frameN = (int) Math.floor(currentTime / stepTime);
				if (frameN > FINALFRAME) {
					frameN = FINALFRAME;
				}

			}
			Vector2 pos = mi.getMousePosition();
			frame.setRotation((float) Math.atan2(pos.y
					- (Settings.getHeight() / 2), pos.x
					- (Settings.getWidth() / 2)));
		} else {
			currentTime = 0;
			frameN = 0;
			frame.setRotation(0);
		}
		frame.setToTextureRegion(frames[frameN]);
		larry.setToWeaponMountPoint(frame.pos);
		if (larry.looksLeft)
			frame.pos.add(XFLIPPEDOFFSET, 0);
		frame.flipY = larry.looksLeft;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(frame);
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
}
