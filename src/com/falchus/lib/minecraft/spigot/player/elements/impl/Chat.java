package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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

	private static final Map<UUID, Supplier<String>> prefixSuppliers = new ConcurrentHashMap<>();
	private static final Map<UUID, String> lastPrefixes = new ConcurrentHashMap<>();
	
	private static final List<Boolean> registered = new ArrayList<>();
	
	private Chat(@NonNull Player player) {
		super(player);
	}
	
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
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix) {
		super.sendUpdating(intervalTicks, () ->
			send(
				prefix
			)
		);
	}
	
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
		Supplier<String> prefixSupplier = prefixSuppliers.get(event.getPlayer().getUniqueId());
		if (prefixSupplier == null) return;
		
		String prefix = prefixSupplier.get();
		if (prefix == null) return;
		
		event.setFormat(prefix + "%2$s");
	}
}
