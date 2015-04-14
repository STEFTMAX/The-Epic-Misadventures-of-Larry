package com.steftmax.temol.state;

import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.content.Level;
import com.steftmax.temol.content.entity.Entity;
import com.steftmax.temol.content.entity.Larry;
import com.steftmax.temol.content.entity.QuadCopter;
import com.steftmax.temol.content.map.old.MapData;
import com.steftmax.temol.content.map.old.TiledMap;
import com.steftmax.temol.graphics.ChaseCamera;
import com.steftmax.temol.graphics.FrameBuffer;
import com.steftmax.temol.graphics.ShaderProgram;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.TextureRegion;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.input.MouseInput;
import com.steftmax.temol.math.AABB;
import com.steftmax.temol.math.QuadTree;
import com.steftmax.temol.physics.PhysicsWorld;
import com.steftmax.temol.resource.GameResources;
import com.steftmax.temol.resource.Settings;
import com.steftmax.temol.resource.TextFile;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
	public QuadTree qt = new QuadTree(4, 0, 1024, 1024, 1024, 10);
	private Sprite aim;
	private GameResources resources = new GameResources();

	ShaderProgram defaultShader;

	// private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game game) {
		super(game);
		defaultShader = new ShaderProgram(null, new TextFile(
				"/shaders/fragment").fileContents);
		final MouseInput mi = game.getMouseInput();

		mi.center();
		mi.grab();

		this.lvl = createLevel();

		this.camera = new ChaseCamera(mi, Settings.getWidth(),
				Settings.getHeight(), 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());

		aim = new Sprite(lvl.manager.getTexture("/gfx/weapons/crosshair_2.png"));
		// light.set(Settings.getWidth() / 2, Settings.getHeight() / 2);

		Display.setVSyncEnabled(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(float delta) {
		// System.out.println(game.getMouseInput().position.x);
		// System.out.println(game.getMouseInput().position.y);
		aim.setScale(camera.getScale());
		aim.setPosition(game.getMouseInput().position.x - aim.origin.x,
				game.getMouseInput().position.y - aim.origin.y);

		// pw.update(delta);
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

		camera.beginFocus();
		batch.begin();
		lvl.map.draw(batch);

		for (Entity ent : lvl.getLevelObjects()) {
			ent.draw(batch);
		}
		batch.flush();

		camera.endFocus();

		aim.draw(batch);

		batch.end();
		defaultShader.unbind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		defaultShader.dispose();
		resources.unload();
	}

	public Level createLevel() {
		resources.load();

		int[][] mapStructure = {
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 },
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 } };

		MapData data = new MapData(mapStructure, 32, 32);
		TiledMap map = new TiledMap(data, resources);
		Level lvl = new Level(resources);
		lvl.setMap(map);
		Larry larry = new Larry(map, 32, 34, game.getKeyboardInput(),
				game.getMouseInput(), resources,lvl);
		lvl.setPlayer(larry);

		return lvl;
	}
}
