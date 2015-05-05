package com.steftmax.temol.event;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pieter3457
 *
 */
public class EventSystem {

	Map<Class<? extends Event>, Set<EventExecutor>> executors = new HashMap<Class<? extends Event>, Set<EventExecutor>>();

	// TODO Exceptions
	public void registerEvents(EventListener listener) {
		Class<? extends EventListener> c = listener.getClass();

		for (Method m : c.getMethods()) {
			
			//Check if the method contains the annotation
			if (m.getAnnotation(EventHandler.class) != null) {

				Class<?>[] params = m.getParameterTypes();
				
				// Make sure there is only one parameter
				if (params.length == 1) {
					
					
					// Check if the paramater is an event
					if (Event.class.isAssignableFrom(params[0])) {
						addToExecutors(params[0], listener, m);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	// Suppress warnings because events are already checked before this is
	// called.
	private void addToExecutors(Class<?> event, EventListener listener,
			Method method) {
		Set<EventExecutor> set;
		if (executors.containsKey(event)) {
			set = executors.get(event);
		} else {
			set = new HashSet<EventExecutor>();

			executors.put((Class<? extends Event>) event, set);
		}

		set.add(new EventExecutor(listener, method));
	}

	public void callEvent(Event event) {
		Set<EventExecutor> executors = this.executors.get(event.getClass());

		for (EventExecutor executor : executors) {
			executor.call(event);
		}
	}
}
