package com.steftmax.temol.math;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.steftmax.temol.content.entity.Entity;
import com.steftmax.temol.resource.memory.Pool;
import com.steftmax.temol.resource.memory.Poolable;

/**
 * A class that stores entities for collision checking
 * 
 * @author pieter3457
 * 
 */

public class QuadTree extends TreeElement {

	final int maxLevels;

	final int maxObjects;

	final LeafPool leafpool;
	final NodePool nodepool;

	final Node base;

	public QuadTree(final int maxObjects, int x, int y, int width, int height,
			int maxLevels) {
		boundary = new AABB(x, y, width, height);
		this.maxLevels = maxLevels;
		this.maxObjects = maxObjects;
		leafpool = new LeafPool(this);
		nodepool = new NodePool(this);
		base = nodepool.getObject();
		//level 0 baseNode
		base.set(x, y, width, height, 0, null);

	}

	public void add(Entity e) {
		base.add(e);
	}

	public void clear() {
		base.clear();
	}
	//
	// public void draw() {
	// GL11.glBegin(GL11.GL_QUADS);
	// GL11.glColor3f(0, 0, 0);
	// GL11.glVertex2i(boundary.x, boundary.y);
	// GL11.glVertex2i(boundary.x + boundary.width, boundary.y);
	// GL11.glVertex2i(boundary.x + boundary.width, boundary.y + height);
	// GL11.glVertex2i(boundary.x, boundary.y + boundary.height);
	//
	// GL11.glColor3f(1f / level, 0.5f / level, 0.25f / level);
	// GL11.glVertex2i(boundary.x + 1, boundary.y + 1);
	// GL11.glVertex2i(boundary.x + boundary.width - 2, boundary.y + 1);
	// GL11.glVertex2i(boundary.x + boundary.width - 2, boundary.y
	// + boundary.height - 2);
	// GL11.glVertex2i(boundary.x + 1, boundary.y + boundary.height - 2);
	//
	// GL11.glEnd();
	//
	// if (isSplit) {
	// for (F child : children) {
	// child.draw();
	// }
	// }
	// }
	//
	// public List<Entity> retrieve(List<Entity> returnObjects, Entity ent) {
	// int index = getChildID(ent, width / 2, height / 2);
	// if (index != -1 && isSplit) {
	// children[index].retrieve(returnObjects, ent);
	// }
	//
	// returnObjects.addAll(entities);
	//
	// return returnObjects;
	// }

}

abstract class TreeElement implements Poolable {
	QuadTree master;
	Node upper;
	int level;

	AABB boundary = new AABB();

	public void reset() {
		boundary.setBounds(0, 0, 0, 0);
		level = 0;
		upper = null;
	}

	abstract void clear();

	abstract void add(Entity ent);
}

class Node extends TreeElement implements Poolable {

	// TreeElement leftTop, rightTop, leftBottom, rightBottom;
	public static final int LEFTBOTTOM = 0, LEFTTOP = 2, RIGHTBOTTOM = 1,
			RIGHTTOP = 3;

	TreeElement[] children = new TreeElement[4];

	public Node(QuadTree master) {
		this.master = master;
	}

	public void reset() {
		super.reset();
		for (int i = 0; i < children.length; i++) {
			children[i] = null;
		}
	}

	public void set(int x, int y, int width, int height, int level, Node upper) {
		boundary.setBounds(x, y, width, height);
		this.level = level;
		int newLevel = level + 1;
		int halfWidth = width / 2;
		int halfHeight = height / 2;

		children[LEFTBOTTOM] = master.leafpool.getObject().set(boundary.x,
				boundary.y, halfWidth, halfHeight, newLevel, this);
		children[RIGHTBOTTOM] = master.leafpool.getObject().set(
				boundary.x + halfWidth, boundary.y, halfWidth, halfHeight,
				newLevel, this);
		children[RIGHTTOP] = master.leafpool.getObject().set(
				boundary.x + halfWidth, boundary.y + halfHeight, halfWidth,
				halfHeight, newLevel, this);
		children[LEFTTOP] = master.leafpool.getObject().set(boundary.x,
				boundary.y + halfHeight, halfWidth, halfHeight, newLevel, this);
	}

	void add(Entity ent) {
		AABB r = ent.hitbox;
		int halfwidth = boundary.width / 2;
		int halfheight = boundary.height / 2;

		int newLevel = level + 1;
		if (r.collides(0, 0, halfheight, halfwidth))
			children[LEFTBOTTOM].add(ent);
		if (r.collides(halfwidth, 0, halfwidth, halfheight))
			children[RIGHTBOTTOM].add(ent);
		if (r.collides(halfwidth, halfheight, halfwidth, halfheight))
			children[RIGHTTOP].add(ent);
		if (r.collides(0, halfheight, halfwidth, halfheight))
			children[LEFTTOP].add(ent);
	}

	public void clear() {
		for (TreeElement el : children) {
			el.clear();
		}
		master.nodepool.eat(this);
	}

	void setToNode(Leaf leaf) {

	}
}

class Leaf extends TreeElement implements Poolable {

	private final HashSet<Entity> entities = new HashSet<Entity>();

	public Leaf(QuadTree master) {
		this.master = master;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.resource.memory.Poolable#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		entities.clear();
	}

	public void split() {
		Node node = toNode();
		for (Entity ent : entities) {
			node.add(ent);
		}
		upper.master.leafpool.eat(this);
	}

	public Node toNode() {
		final Node node = master.nodepool.getObject();
		node.set(boundary.x, boundary.y, boundary.width, boundary.height,
				level, node);
		return node;
	}

	public Leaf set(int x, int y, int width, int height, int level, Node upper) {
		this.upper = upper;
		boundary.setBounds(x, y, width, height);
		this.level = level;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.math.TreeElement#clear()
	 */
	@Override
	public void clear() {
		master.leafpool.eat(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.temol.math.TreeElement#add(com.steftmax.temol.content.entity
	 * .Entity, int, int)
	 */
	@Override
	void add(Entity ent) {

		entities.add(ent);
		if (entities.size() > master.maxObjects
				&& level + 1 <= master.maxLevels) {
			split();
		}
	}
}

// THE POOLZ FOR DEM OBJECTZ

class NodePool extends Pool<Node> {

	private final QuadTree master;

	public NodePool(QuadTree master) {
		this.master = master;
	}

	@Override
	protected Node newObject() {
		return new Node(master);
	}

}

class LeafPool extends Pool<Leaf> {

	private final QuadTree master;

	public LeafPool(QuadTree master) {
		this.master = master;
	}

	@Override
	protected Leaf newObject() {
		return new Leaf(master);
	}

}
