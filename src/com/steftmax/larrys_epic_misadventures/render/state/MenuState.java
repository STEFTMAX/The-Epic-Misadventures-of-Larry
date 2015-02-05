package com.steftmax.larrys_epic_misadventures.render.state;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.LarrysEpicMisadventures;
import com.steftmax.larrys_epic_misadventures.graphics.Camera;
import com.steftmax.larrys_epic_misadventures.graphics.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.graphics.StaticCamera;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.Texture;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.TextureRegion;
import com.steftmax.larrys_epic_misadventures.render.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.resource.MenuResources;

/**
 * @author pieter3457
 *
 */
public class MenuState extends State {

	private final Button play, settings;
	private final MenuResources resources = new MenuResources();
	private Camera cam;
	private Sprite background;

	private enum Screen {
		MENU, SETTINGS;
	}

	private Screen screen = Screen.MENU;

	private final static int PLAYBUTTON_X = 240, PLAYBUTTON_Y = 250,
			SETTINGSBUTTON_X = 340, SETTINGSBUTTON_Y = 250;

	public MenuState(Game game) {
		super(game);

		final MouseInput mi = game.getMouseInput();

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
		if (play.consumePressed()) {

			deleteResources();
			game.changeState(new GameState(game, ((LarrysEpicMisadventures) game).createLevel()));

			return;
		}

		if (settings.consumePressed()) {
			screen = Screen.SETTINGS;
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
		switch (screen) {
		case MENU:
			play.draw(batch);
			settings.draw(batch);
			break;
		case SETTINGS:

			break;

		}

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
		game.getMouseInput().clear();

	}

}
