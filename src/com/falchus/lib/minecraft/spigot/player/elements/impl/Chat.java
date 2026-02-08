package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;

import lombok.NonNull;

public class Chat extends PlayerElement implements Listener {

	private static final Map<UUID, Supplier<String>> prefixSuppliers = new HashMap<>();
	private static final Map<UUID, String> lastPrefixes = new HashMap<>();
	
	private static final List<Boolean> registered = new ArrayList<>();
	
	private Chat(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sets one-time.
	 */
	public void send(@NonNull Supplier<String> prefix) {
		if (registered.size() == 0) {
			Bukkit.getPluginManager().registerEvents(this, plugin);
			registered.add(true);
		}
		
		prefixSuppliers.put(player.getUniqueId(), prefix);
		
		updateRunnable = () -> {
			String lastPrefix = lastPrefixes.get(player.getUniqueId());
			String newPrefix = prefixSuppliers.get(player.getUniqueId()).get();
			
			if (lastPrefix == null || !newPrefix.equals(lastPrefix)) {
				lastPrefixes.put(player.getUniqueId(), newPrefix);
			}
		};
		update();
	}
	
	/**
	 * Updates periodically.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix) {
		super.sendUpdating(intervalTicks, () ->
			send(
				prefix
			)
		);
	}
	
	/**
	 * Removes the chat element.
	 */
	public void remove() {
		super.remove();
		
		prefixSuppliers.remove(player.getUniqueId());
		lastPrefixes.remove(player.getUniqueId());
		
		if (registered.size() == 1 && registered.getFirst() == true) {
			HandlerList.unregisterAll(this);
			registered.clear();
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		String prefix = prefixSuppliers.get(event.getPlayer().getUniqueId()).get();
		if (prefix == null) return;
		
		event.setFormat(prefix + "%2$s");
	}
}
