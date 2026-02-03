package com.falchus.lib.minecraft.spigot.player.elements;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Abstract base class for player-related elements.
 */
@RequiredArgsConstructor
public abstract class PlayerElement {

	protected final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	protected final Player player;
	
    private static final Map<Class<? extends PlayerElement>, Map<UUID, PlayerElement>> instances = new HashMap<>();
    private static final Map<Class<? extends PlayerElement>, Map<UUID, BukkitTask>> tasks = new HashMap<>();
    
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
	 * Sends the element to the player repeatedly with a fixed interval.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Runnable runnable) {
	    Map<UUID, BukkitTask> map = tasks.computeIfAbsent(getClass(), c -> new HashMap<>());
	    
		BukkitTask oldTask = map.get(player.getUniqueId());
		if (oldTask != null) {
			remove();
		}
		
		BukkitTask task = new BukkitRunnable() {
			@Override
			public void run() {
				if (!player.isOnline()) {
					remove();
					return;
				}
				runnable.run();
			}
		}.runTaskTimer(plugin, 0, intervalTicks);
		
		map.put(player.getUniqueId(), task);
	}
	
	/**
	 * Removes this element and cancels any schedules repeating tasks.
	 */
	public void remove() {
		Map<UUID, BukkitTask> map = tasks.get(getClass());
		if (map != null) {
	        BukkitTask task = map.remove(player.getUniqueId());
	        if (task != null) {
	            task.cancel();
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
		Map<UUID, PlayerElement> map = instances.computeIfAbsent(clazz, c -> new HashMap<>());
		
		return (T) map.computeIfAbsent(player.getUniqueId(), uuid -> {
			try {
				Constructor<T> ctor = clazz.getDeclaredConstructor(Player.class);
	            ctor.setAccessible(true);
	            return ctor.newInstance(player);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
	}
}
