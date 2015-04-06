package com.steftmax.temol.render.state;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.graphics.Color;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.font.BitmapFont;
import com.steftmax.temol.render.input.KeyboardCharacterListener;
import com.steftmax.temol.render.input.KeyboardInput;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.MapEditorResources;
import com.steftmax.temol.resource.ResourceManager;

/**
 * @author pieter3457
 *
 */
public class MapEditorState extends State implements KeyboardCharacterListener {

	ResourceManager rm;
	private KeyboardInput ki;
	private MouseInput mi;
	private BitmapFont font;

	/**
	 * @param game
	 */
	public MapEditorState(Game game) {
		super(game);
		rm = new MapEditorResources();
		rm.load();
		mi = game.getMouseInput();
		ki = game.getKeyboardInput();
		ki.addListener(this);

		Display.setVSyncEnabled(true);
		font = new BitmapFont("1234567890.,!?;:ABCDEFGHIJKLMNOPQRSTUVWXYZ", rm
				.getSpriteSheet("gfx/font/font1.png").getFrames(), new Color(
				.5F, .7F, .3F, 1F));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.render.Updatable#update(long)
	 */
	float time = 0;

	@Override
	public void update(float delta) {
		time += delta;
		time %= 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.graphics.Drawable#draw(com.steftmax.temol.graphics
	 * .SpriteBatch)
	 */

	private final static String MAPNAME = "MAPNAME", MAPNAME_ = "MAPNAME.";

	@Override
	public void draw(SpriteBatch batch) {
		batch.begin();

		if (time >= 1f) {
			font.draw(batch, MAPNAME_ + sb, 10, 10);
		} else {
			font.draw(batch, MAPNAME + sb, 10, 10);
		}

		batch.end();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.render.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		ki.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.render.input.KeyboardCharacterListener#onCharacterPress
	 * (char)
	 */
	StringBuilder sb = new StringBuilder();

	@Override
	public void onCharacterPress(char c) {
		switch (c) {
		case '\b':
			sb.deleteCharAt(sb.length() - 1);
			break;
		case '\n':
			break;
		default:
			sb.append(c);
			break;
		}
	}

}
