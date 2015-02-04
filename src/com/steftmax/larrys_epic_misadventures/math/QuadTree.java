package com.steftmax.larrys_epic_misadventures.math;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.steftmax.larrys_epic_misadventures.content.entity.Entity;

/**
 * @author pieter3457 A class that stores entities for collision checking
 * 
 */
public class QuadTree {
	private final QuadTree[] children = new QuadTree[4];

	private int x, y, width, height;

	private final HashSet<Entity> entities = new HashSet<Entity>();

	public int level, maxLevels;

	private boolean isSplit = false;

	private int maxObjects;

	private final static int TOPLEFT = 1, TOPRIGHT = 0, BOTTOMLEFT = 2,
			BOTTOMRIGHT = 3;

	private QuadTree(final int maxObjects, int x, int y, int width, int height,
			int maxLevels, int level) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.maxLevels = maxLevels;
		this.maxObjects = maxObjects;
		this.level = level;
	}

	public QuadTree(final int maxObjects, int width, int height, int maxLevels) {
		this(maxObjects, 0, 0, width, height, maxLevels, 0);
	}

	public void add(Entity e) {
		int id = getChildID(e, width / 2, height / 2);
		if (id < 0) {
			entities.add(e);
		} else {
			if (isSplit) {
				children[id].add(e);
			} else {
				if (entities.size() + 1 > maxObjects
						&& this.maxLevels > this.level) {
					split();
					children[id].add(e);
				} else {
					entities.add(e);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void split() {

		int halfwidth = this.width / 2;
		int halfheight = this.height / 2;
		int newlevel = this.level + 1;

		children[TOPRIGHT] = new QuadTree(maxObjects, x + halfwidth, y,
				halfwidth, halfheight, maxLevels, newlevel);
		children[TOPLEFT] = new QuadTree(maxObjects, x, y, halfwidth,
				halfheight, maxLevels, newlevel);
		children[BOTTOMLEFT] = new QuadTree(maxObjects, x, y + halfheight,
				halfwidth, halfheight, maxLevels, newlevel);
		children[BOTTOMRIGHT] = new QuadTree(maxObjects, x + halfwidth, y
				+ halfheight, halfwidth, halfheight, maxLevels, newlevel);

		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			int id = getChildID(e, halfwidth, halfheight);
			if (id >= 0) {
				children[id].add(e);
				iter.remove();
			}
		}
		isSplit = true;
	}

	private int getChildID(Entity ent, int halfwidth, int halfheight) {
		AABB r = ent.getHitbox();

		int index = -1;
		int verticalMidpoint = x + halfwidth;
		int horizontalMidpoint = y + halfwidth;

		boolean topQuadrant = (r.y < horizontalMidpoint && r.y + r.height < horizontalMidpoint);
		boolean bottomQuadrant = (r.y > horizontalMidpoint);

		if (r.x < verticalMidpoint && r.x + r.width < verticalMidpoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}

		else if (r.x > verticalMidpoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	// TODO Pooling
	public void clear() {
		if (isSplit) {
			for (QuadTree child : children) {
				child.clear();
			}
		} else {
			entities.clear();
		}
		this.isSplit = false;
	}

	public void draw() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0, 0, 0);
		GL11.glVertex2i(x, y);
		GL11.glVertex2i(x + width, y);
		GL11.glVertex2i(x + width, y + height);
		GL11.glVertex2i(x, y + height);

		GL11.glColor3f(1f / level, 0.5f / level, 0.25f / level);
		GL11.glVertex2i(x + 1, y + 1);
		GL11.glVertex2i(x + width - 2, y + 1);
		GL11.glVertex2i(x + width - 2, y + height - 2);
		GL11.glVertex2i(x + 1, y + height - 2);

		GL11.glEnd();

		if (isSplit) {
			for (QuadTree child : children) {
				child.draw();
			}
		}
	}

	public List<Entity> retrieve(List<Entity> returnObjects, Entity ent) {
		int index = getChildID(ent, width / 2, height / 2);
		if (index != -1 && isSplit) {
			children[index].retrieve(returnObjects, ent);
		}

		returnObjects.addAll(entities);

		return returnObjects;
	}

}
