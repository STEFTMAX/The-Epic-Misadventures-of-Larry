package com.steftmax.larrys_epic_misadventures.state;

import java.util.ArrayList;
import java.util.List;
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
import com.steftmax.larrys_epic_misadventures.math.QuadTree;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
	public QuadTree qt = new QuadTree(4, 1024, 1024, 5);
	private Sprite aim;

	private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game game, Level lvl, MouseInput mi, KeyboardInput ki) {
		super(game, mi, ki);
		mi.grab();
		
		this.lvl = lvl;
		this.camera = new ChaseCamera(mi, 1280, 720, 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());

		aim = new Sprite(lvl.manager.getTexture("/gfx/weapons/crosshair_2.png"));
		aim.setScale(2f);
		mi.position.set(0, 0);
		Display.setVSyncEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		
		qt.clear();
		ki.update(delta);
		mi.update(delta);
		aim.set(mi.position.x- aim.width, mi.position.y - aim.height);

		Set<Entity> set = lvl.getLevelObjects();

		for (Entity ent : set) {

			ent.update(delta);
			qt.add(ent);

		}
		// TODO PERFOMRANCE BITCH master quadtree keeps all entered Entities

		for (Entity ent : set) {

			returnObjects.clear();
			qt.retrieve(returnObjects, ent);

			for (int i = 0; i < returnObjects.size(); i++) {
				for (int j = i + 1; j < returnObjects.size(); j++) {

					if (returnObjects.get(i).hitbox.collides(returnObjects
							.get(j).hitbox)) {
						// returnObjects.
					}
				}
			}

			// AABB box1 = ent1.hitbox;
			// for (Entity ent2 : returnObjects) {
			// AABB box2 = ent2.hitbox;
			//
			// if (box1.collides(box2)) {
			// ent1.onCollide(ent2);
			// ent2.onCollide(ent1);
			// break;
			// }
			// }
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
