package com.steftmax.temol.resource.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pieter3457
 *
 */
public abstract class Pool<P extends Poolable> {
	int maxObjects;

	List<P> pooledObjects = new ArrayList<P>();

	public Pool() {
		this(0);
	}

	/**
	 * @param maxObjects
	 *            how many objects this pool can maximally handle.
	 */
	public Pool(int maxObjects) {
		this.maxObjects = maxObjects;
	}
	
	public void eat(P object) {
		if (maxObjects < pooledObjects.size() + 1) return;
		object.reset();
		pooledObjects.add(object);
	}
	
	protected abstract P newObject();
	
	public P getObject() {
		if (pooledObjects.size() == 0) {
			return newObject();
		} else {
			final P temp = pooledObjects.get(0);
			pooledObjects.remove(temp);
			return temp;
		}
	}
}