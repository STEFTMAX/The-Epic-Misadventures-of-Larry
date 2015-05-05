package com.steftmax.temol.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author pieter3457
 *
 */
public class EventExecutor {
	private EventListener listener;
	private Method method;

	public EventExecutor(EventListener listener, Method method) {
		this.listener = listener;
		this.method = method;
	}

	/**
	 * @param event
	 */
	public void call(Event event) {
		try {
			method.invoke(listener, event);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
