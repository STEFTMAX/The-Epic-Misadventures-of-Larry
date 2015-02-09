package com.steftmax.temol.render.state;

import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.content.Level;
import com.steftmax.temol.content.entity.Entity;
import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.graphics.ChaseCamera;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.Settings;

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
		aim.setContainmentTest(false);
		//light.set(Settings.getWidth() / 2, Settings.getHeight() / 2);

		Display.setVSyncEnabled(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		// System.out.println(game.getMouseInput().position.x);
		// System.out.println(game.getMouseInput().position.y);
		aim.setScale(camera.getScale() / 1.5f);
		aim.set(game.getMouseInput().position.x - aim.getScaledWidth() / 2,
				game.getMouseInput().position.y - aim.getScaledHeight() / 2);

		Set<Entity> set = lvl.getLevelObjects();

		for (Entity ent : set) {

			ent.update(delta);

		}
	}

	/**
	 * 
	 */
	public void draw(SpriteBatch batch) {

		final AABB viewingarea = camera.getViewingArea();
		camera.beginFocus();
		batch.begin(viewingarea);
		lvl.map.draw(batch);

		for (Entity ent : lvl.getLevelObjects()) {
			ent.draw(batch);
		}
		lvl.map.drawLights(batch);
		batch.flush();

		camera.endFocus();

		batch.draw(aim);

		batch.end();
		// glBegin(GL_QUADS);
		// // glVertex2i(viewingarea.x, viewingarea.y);
		// // glVertex2i(viewingarea.x + viewingarea.width, viewingarea.y);
		// // glVertex2i(viewingarea.x + viewingarea.width, viewingarea.y
		// // + viewingarea.height);
		// // glVertex2i(viewingarea.x, viewingarea.y + viewingarea.height);
		//
		// glColor4f(0, 0, 0, .8f);
		// glVertex2i(0, 0);
		// glVertex2i(Settings.getWidth(), 0);
		// glVertex2i(Settings.getWidth(), Settings.getHeight());
		// glVertex2i(0, Settings.getHeight());
		// glEnd();
		// glColor3f(1, 1, 1);
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
