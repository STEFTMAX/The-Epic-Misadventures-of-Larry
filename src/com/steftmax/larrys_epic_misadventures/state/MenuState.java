package com.steftmax.larrys_epic_misadventures.state;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.LarrysEpicMisadventures;
import com.steftmax.larrys_epic_misadventures.draw.Camera;
import com.steftmax.larrys_epic_misadventures.draw.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.draw.StaticCamera;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.resource.MenuResources;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.sprite.TextureRegion;

/**
 * @author pieter3457
 *
 */
public class MenuState extends State {

	private final Button play, settings;
	private final MenuResources resources = new MenuResources();
	private Camera cam;
	private Sprite background;

	private final static int PLAYBUTTON_X = 240, PLAYBUTTON_Y = 250,
			SETTINGSBUTTON_X = 340, SETTINGSBUTTON_Y = 250;

	public MenuState(Game game, MouseInput mi, KeyboardInput ki) {
		super(game, mi, ki);
		mi.unGrab();

		cam = new StaticCamera(2f, 0, 0);
		mi.setCamera(cam);

		resources.load();

		background = new Sprite(resources.getTexture("gfx/menu.png"), 0, 0);

		final Texture sheet = resources.getTexture("gfx/sheet_buttons.png");
		final int width = 64, height = 16;

		play = new Button(mi, PLAYBUTTON_X, PLAYBUTTON_Y, new TextureRegion(
				sheet, 0, 2 * height, width, height), new TextureRegion(sheet,
				0, height, width, height), new TextureRegion(sheet, 0, 0,
				width, height));

		settings = new Button(mi, SETTINGSBUTTON_X, SETTINGSBUTTON_Y,
				new TextureRegion(sheet, width, 2 * height, width, height),
				new TextureRegion(sheet, width, height, width, height),
				new TextureRegion(sheet, width, 0, width, height));
		
		Display.setVSyncEnabled(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		ki.update(delta);
		mi.update(delta);
		if (play.consumePressed()) {

			deleteResources();
			game.changeState(new GameState(game, LarrysEpicMisadventures
					.createLevel(ki, mi), mi, ki));
			
			return;
		}

		if (settings.consumePressed()) {
			game.stop();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.draw.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.draw.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {

		cam.beginFocus();
		batch.begin();

		batch.draw(background);

		play.draw(batch);
		settings.draw(batch);

		batch.end();
		cam.endFocus();

		Display.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		resources.unload();
		mi.clear();
		
	}

}
