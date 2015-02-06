package com.steftmax.larrys_epic_misadventures.render.state;

import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.content.Level;
import com.steftmax.larrys_epic_misadventures.content.entity.Entity;
import com.steftmax.larrys_epic_misadventures.content.entity.Larry;
import com.steftmax.larrys_epic_misadventures.graphics.ChaseCamera;
import com.steftmax.larrys_epic_misadventures.graphics.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.render.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.resource.Settings;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
	// public QuadTree qt = new QuadTree(4, 1024, 1024, 5);
	private Sprite aim;

	// private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game game, Level lvl) {
		super(game);
		final MouseInput mi = game.getMouseInput();
		
		mi.center();
		mi.grab();
		
		this.lvl = lvl;
		
		this.camera = new ChaseCamera(mi, Settings.getWidth(),
				Settings.getHeight(), 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());

		aim = new Sprite(lvl.manager.getTexture("/gfx/weapons/crosshair_2.png"));
		aim.setScale(2f);

		Display.setVSyncEnabled(false);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
//		System.out.println(game.getMouseInput().position.x);
//		System.out.println(game.getMouseInput().position.y);
		aim.set(game.getMouseInput().position.x - aim.width, game.getMouseInput().position.y - aim.height);

		Set<Entity> set = lvl.getLevelObjects();

		for (Entity ent : set) {

			ent.update(delta);

		}
	}

	/**
	 * 
	 */
	public void draw(SpriteBatch batch) {

		camera.beginFocus();
		batch.begin(camera.getViewingArea());
		lvl.map.draw(batch);

		for (Entity ent : lvl.getLevelObjects()) {
			ent.draw(batch);
		}
		batch.flush();

		camera.endFocus();

		batch.draw(aim);

		batch.end();

		Display.update();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		// TODO Auto-generated method stub

	}
}
