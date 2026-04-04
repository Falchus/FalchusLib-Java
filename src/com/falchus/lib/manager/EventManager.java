package com.falchus.lib.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.falchus.lib.events.Cancellable;
import com.falchus.lib.events.Event;
import com.falchus.lib.events.EventHandler;
import com.falchus.lib.events.listener.Listener;
import com.falchus.lib.events.listener.RegisteredListener;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EventManager {

	private final Map<Class<? extends Event>, List<RegisteredListener>> listeners = new ConcurrentHashMap<>();
	
	@SuppressWarnings("unchecked")
	public static void registerListener(Listener listener) {
		for (Method method : listener.getClass().getDeclaredMethods()) {
			if (!method.isAnnotationPresent(EventHandler.class)) continue;
			
			Class<?>[] params = method.getParameterTypes();
			if (params.length != 1 || !Event.class.isAssignableFrom(params[0])) continue;
			
			Class<? extends Event> clazz = (Class<? extends Event>) params[0];
			EventHandler handler = method.getAnnotation(EventHandler.class);
			
			method.setAccessible(true);
			
			List<RegisteredListener> listeners = EventManager.listeners.computeIfAbsent(clazz, k -> new CopyOnWriteArrayList<>());
			listeners.add(new RegisteredListener(
				listener,
				event -> {
					try {
						method.invoke(listener, event);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				},
				handler
			));
			listeners.sort(Comparator.comparingInt(k -> k.getPriority().ordinal()));
		}
	}
	
	public static void unregisterListener(Listener listener) {
		for (List<RegisteredListener> list : listeners.values()) {
			list.removeIf(registered -> registered.getListener().equals(listener));
        }
	}
	
	public static void callEvent(Event event) {
		List<RegisteredListener> listeners = new ArrayList<>();
		
		Class<?> clazz = event.getClass();
		while (clazz != null && Event.class.isAssignableFrom(clazz)) {
			List<RegisteredListener> list = EventManager.listeners.get(clazz);
			if (list != null) {
				listeners.addAll(list);
			}
			clazz = clazz.getSuperclass();
		}
		
		if (listeners.isEmpty()) return;
		
		listeners.sort(Comparator.comparingInt(k -> k.getPriority().ordinal()));
		
		for (RegisteredListener listener : listeners) {
			if (event instanceof Cancellable cancellable) {
				if (cancellable.isCancelled() && listener.isIgnoreCancelled()) continue;
			}
			
			if (event.isAsync()) {
				CompletableFuture.runAsync(() -> listener.execute(event));
			} else {
				try {
					listener.execute(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
