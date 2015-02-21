package com.steftmax.temol.render.state;

import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.content.Level;
import com.steftmax.temol.content.entity.Entity;
import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.graphics.ChaseCamera;
import com.steftmax.temol.graphics.FrameBuffer;
import com.steftmax.temol.graphics.ShaderProgram;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.math.QuadTree;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.Settings;
import com.steftmax.temol.resource.TextFile;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
	public QuadTree qt = new QuadTree(4, 0, 0, 1024, 1024, 10);
	private Sprite aim;

	int lightSize = 256;

	FrameBuffer occludersFBO = new FrameBuffer(lightSize, lightSize);
	FrameBuffer shadowMapFBO = new FrameBuffer(lightSize, 1);
	TextureRegion occluders = new TextureRegion(occludersFBO.getTexture());

	ShaderProgram defaultShader;

	// private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game game, Level lvl) {
		super(game);
		defaultShader = new ShaderProgram(null, new TextFile(
				"/shaders/fragment").fileContents);
		final MouseInput mi = game.getMouseInput();

		mi.center();
		mi.grab();

		this.lvl = lvl;

		this.camera = new ChaseCamera(mi, Settings.getWidth(),
				Settings.getHeight(), 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());

		aim = new Sprite(lvl.manager.getTexture("/gfx/weapons/crosshair_2.png"));
		aim.setContainmentTest(false);
		// light.set(Settings.getWidth() / 2, Settings.getHeight() / 2);

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
			qt.add(ent);
			ent.update(delta);

		}
		qt.clear();
	}

	/**
	 * 
	 */
	public void draw(SpriteBatch batch) {
		defaultShader.bind();

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
		defaultShader.unbind();
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
