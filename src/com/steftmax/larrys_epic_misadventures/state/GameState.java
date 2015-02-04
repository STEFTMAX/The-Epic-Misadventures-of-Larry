package com.steftmax.larrys_epic_misadventures.state;

import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.draw.ChaseCamera;
import com.steftmax.larrys_epic_misadventures.draw.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.entity.Entity;
import com.steftmax.larrys_epic_misadventures.entity.Larry;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.resource.Settings;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
//	public QuadTree qt = new QuadTree(4, 1024, 1024, 5);
	private Sprite aim;

//	private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game game, Level lvl, MouseInput mi, KeyboardInput ki) {
		super(game, mi, ki);
		mi.grab();
		
		this.lvl = lvl;
		this.camera = new ChaseCamera(mi, Settings.getWidth(), Settings.getHeight(), 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());

		aim = new Sprite(lvl.manager.getTexture("/gfx/weapons/crosshair_2.png"));
		aim.setScale(2f);
		mi.position.set(0, 0);
		
		
		Display.setVSyncEnabled(false);
		
		
//		try {
//			DisplayMode displayMode = null;
//			DisplayMode[] modes;
//			modes = Display.getAvailableDisplayModes();
//			for (int i = 0; i < modes.length; i++) {
//				if (modes[i].getWidth() == 1920 && modes[i].getHeight() == 1080
//						&& modes[i].isFullscreenCapable()) {
//					displayMode = modes[i];
//				}
//			}
//			Display.setDisplayMode(displayMode);
//			Display.setFullscreen(true);
//		} catch (LWJGLException e1) {
//			e1.printStackTrace();
//		}
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
		aim.set(mi.position.x- aim.width, mi.position.y - aim.height);

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

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		// TODO Auto-generated method stub
		
	}
}
