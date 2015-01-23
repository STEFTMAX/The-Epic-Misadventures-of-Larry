package com.steftmax.larrys_epic_misadventures.update;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.lwjgl.opengl.Display;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.draw.ChaseCamera;
import com.steftmax.larrys_epic_misadventures.entity.Entity;
import com.steftmax.larrys_epic_misadventures.entity.Larry;
import com.steftmax.larrys_epic_misadventures.input.KeyboardInput;
import com.steftmax.larrys_epic_misadventures.input.MouseInput;
import com.steftmax.larrys_epic_misadventures.level.Level;
import com.steftmax.larrys_epic_misadventures.math.QuadTree;

/**
 * @author pieter3457
 *
 */
public class GameState extends State {

	public final ChaseCamera camera;
	public final Level lvl;
	private final Game g;
	private KeyboardInput ki;
	private MouseInput mi;

	public QuadTree qt = new QuadTree(4, 1024, 1024, 5);

	private final List<Entity> returnObjects = new ArrayList<Entity>();

	public GameState(Game g, Level lvl, MouseInput mi, KeyboardInput ki) {

		this.mi = mi;
		this.ki = ki;
		this.g = g;
		this.lvl = lvl;
		this.camera = new ChaseCamera(mi, 1280, 720, 5f, 2f, 0.001f);
		camera.lock(((Larry) lvl.player).getLockingPosition());
		
		
		
		
		
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, 1280, 720, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClearColor(.5f, .5f, .5f, 1);
		
		glDisable(GL_DEPTH_TEST);
		
		glLoadIdentity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		Display.setTitle("FPS: "
				+ (int) (1 / TimeScaler.nanosToSecondsF(delta)));
		qt.clear();
		ki.update(delta);
		mi.update(delta);

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
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		camera.beginFocus();
		lvl.map.draw();
		
		for (Entity ent : lvl.getLevelObjects()) {
			ent.draw();
		}
		camera.endFocus();
		
		// Draw hud here I guess
		Display.update();
	}
}
