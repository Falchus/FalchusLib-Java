package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	private static final Map<Player, Supplier<String>> prefixSuppliers = new HashMap<>();
	private static final Map<Player, String> lastPrefixes = new HashMap<>();
	
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
		
		prefixSuppliers.put(player, prefix);
		
		updateRunnable = () -> {
			String lastPrefix = lastPrefixes.get(player);
			String newPrefix = prefixSuppliers.get(player).get();
			
			if (lastPrefix == null || !newPrefix.equals(lastPrefix)) {
				lastPrefixes.put(player, newPrefix);
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
		
		prefixSuppliers.remove(player);
		lastPrefixes.remove(player);
		
		if (registered.size() == 1 && registered.getFirst() == true) {
			HandlerList.unregisterAll(this);
			registered.clear();
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		String prefix = prefixSuppliers.get(event.getPlayer()).get();
		if (prefix == null) return;
		
		event.setFormat(prefix + "%2$s");
	}
}
