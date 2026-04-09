package com.falchus.lib.minecraft.spigot.player.elements;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.task.Task;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Abstract base class for player-related elements.
 */
@RequiredArgsConstructor
public abstract class PlayerElement {

	protected final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	protected final Player player;
	
    private static final Map<Class<? extends PlayerElement>, Map<UUID, PlayerElement>> instances = new ConcurrentHashMap<>();
    private static final Map<Class<? extends PlayerElement>, Map<UUID, Task>> tasks = new ConcurrentHashMap<>();
    
    protected Runnable updateRunnable;
	
	/**
	 * Updates the element manually.
	 */
	public void update() {
		if (updateRunnable == null) return;
		
		if (!player.isOnline()) {
			remove();
			return;
		}
		updateRunnable.run();
	}
	
	/**
	 * Updates all online players manually.
	 */
	public static <T extends PlayerElement> void updateAll(@NonNull Class<T> clazz) {
		Map<UUID, PlayerElement> map = instances.get(clazz);
		if (map == null) return;
		
		map.values().forEach(PlayerElement::update);
	}
	
	/**
	 * Sends the element to the player repeatedly with a fixed interval (in {@link TimeUnit#MILLISECONDS}).
	 */
	public void sendUpdating(long intervalTicks, @NonNull Runnable runnable) {
	    Map<UUID, Task> map = tasks.computeIfAbsent(getClass(), c -> new ConcurrentHashMap<>());
	    
	    Task oldTask = map.get(player.getUniqueId());
		if (oldTask != null) {
			remove();
		}
		
		Task task = Task.runTimer(() -> {
			if (!player.isOnline()) {
				remove();
				return;
			}
			runnable.run();
		}, intervalTicks, TimeUnit.MILLISECONDS);
		
		map.put(player.getUniqueId(), task);
	}
	
	/**
	 * Removes this element and cancels any schedules repeating tasks.
	 */
	public void remove() {
		Map<UUID, Task> map = tasks.get(getClass());
		if (map != null) {
			Task task = map.remove(player.getUniqueId());
	        if (task != null) {
	            task.end();
	        }
		}
		
		Map<UUID, PlayerElement> instance = instances.get(getClass());
		if (instance != null) {
			instance.remove(player.getUniqueId());
		}
	}
	
	/**
	 * Retrieves a singleton instance of a PlayerElement subclass for a given player.
	 * If it does not exist, it is created via reflection using a constructor that accepts a Player parameter.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends PlayerElement> T get(@NonNull Class<T> clazz, @NonNull Player player) {
		if (!PlayerElement.class.isAssignableFrom(clazz)) return null;
		Map<UUID, PlayerElement> map = instances.computeIfAbsent(clazz, c -> new ConcurrentHashMap<>());
		
		return (T) map.computeIfAbsent(player.getUniqueId(), uuid -> {
			try {
				return (T) new ClassInstanceBuilder(clazz)
						.withParams(
							Map.of(
								Player.class,
								player
							)
						)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
	}
}
