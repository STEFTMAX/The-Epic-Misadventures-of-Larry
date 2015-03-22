package com.steftmax.temol.content.entity.weapon;

import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.ResourceManager;

/**
 * @author pieter3457
 *
 */
public class Bow extends Weapon {

	Sprite frame = new Sprite();
	boolean playing;
	private TextureRegion[] frames;
	private Larry larry;
	private static final int YOFFSET = 0, XOFFSET = 0;

	public Bow(ResourceManager gameResources, MouseInput mi, Larry larry) {
		super(gameResources, mi);
		this.larry = larry;
		frames = gameResources.getSpriteSheet("gfx/bow top.png").getFrames();
		frame.setToTextureRegion(frames[0]);
	}

	int frameN = 0;
	long currentTime;
	long stepTime = 100000000;

	public void update(long delta) {
		if (mi.primaryDown()) {
			if (frameN < 6) {
				currentTime += delta;
				frameN = (int) Math.floor(currentTime / stepTime);
				if (frameN > 6) {
					frameN = 6;
				}
			}
		} else {
			currentTime = 0;
			frameN = 0;
		}
		frame.setToTextureRegion(frames[frameN]);

		frame.set(larry.position.x + XOFFSET, larry.position.y + YOFFSET);
		frame.flipY = larry.looksLeft;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(frame);
	}
}
