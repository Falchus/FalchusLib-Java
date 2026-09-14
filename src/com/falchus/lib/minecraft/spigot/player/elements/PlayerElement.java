package com.falchus.lib.minecraft.spigot.player.elements;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.task.SpigotTask;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Abstract base class for player-related elements.
 */
@RequiredArgsConstructor
public class PlayerElement {

	protected static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	protected final Player player;
	protected final boolean async;
	
    private static final Map<Class<? extends PlayerElement>, Map<UUID, PlayerElement>> instances = new ConcurrentHashMap<>();
    private static final Map<Class<? extends PlayerElement>, Map<UUID, SpigotTask>> tasks = new ConcurrentHashMap<>();
    
    protected Runnable updateRunnable;
    protected int frame = 0;
    
    public PlayerElement(@NonNull Player player) {
    	this(player, false);
    }
	
	/**
	 * Updates the element manually.
	 */
	public void update() {
		if (!player.isOnline()) {
			remove();
			return;
		}
		
		if (updateRunnable == null) return;
		SpigotTask task = SpigotTask.of(updateRunnable);
		if (async) {
			task.runAsync();
		} else {
			task.run();
		}
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
	    Map<UUID, SpigotTask> map = tasks.computeIfAbsent(getClass(), c -> new ConcurrentHashMap<>());
	    
	    SpigotTask oldTask = map.remove(player.getUniqueId());
		if (oldTask != null) {
			oldTask.end();
		}
		frame = 0;
		
		SpigotTask task = new SpigotTask() {
			@Override
			protected void onRun(int tick) {
				if (!player.isOnline()) {
					remove();
					return;
				}
				frame = tick;
				runnable.run();
			}
		};
		if (async) {
			task.runTimerAsync(intervalTicks, TimeUnit.MILLISECONDS);
		} else {
			task.runTimer(intervalTicks, TimeUnit.MILLISECONDS);
		}
		
		map.put(player.getUniqueId(), task);
	}
	
	/**
	 * Removes this element and cancels any schedules repeating tasks.
	 */
	public void remove() {
		Map<UUID, SpigotTask> map = tasks.get(getClass());
		if (map != null) {
			SpigotTask task = map.remove(player.getUniqueId());
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
		
		PlayerElement existing = map.get(player.getUniqueId());
		if (existing != null && existing.player != player) {
			existing.remove();
		}
		
		return (T) map.computeIfAbsent(player.getUniqueId(), uuid ->
			(T) new ClassInstanceBuilder(
				clazz
			).withParams(
				Map.of(
					Player.class,
					player
				)
			).build()
		);
	}
}
