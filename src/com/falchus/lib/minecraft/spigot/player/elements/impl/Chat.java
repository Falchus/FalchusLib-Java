package com.falchus.lib.minecraft.spigot.player.elements.impl;

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

	private Supplier<String> prefixSupplier;
	private String lastPrefix = "";
	
	private boolean registered = false;
	
	private Chat(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sets one-time.
	 */
	public void send(@NonNull Supplier<String> prefix) {
		prefixSupplier = prefix;
		
		updateRunnable = () -> {
			String newPrefix = prefixSupplier.get();
			
			if (lastPrefix == "" || !newPrefix.equals(lastPrefix)) {
				lastPrefix = newPrefix;
			}
		};
		update();
		
		if (!registered) {
			Bukkit.getPluginManager().registerEvents(this, plugin);
			registered = true;
		}
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
		
		if (registered) {
			HandlerList.unregisterAll(this);
			registered = false;
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		if (!event.getPlayer().equals(player)) return;
		event.setFormat(prefixSupplier.get() + "%2$s");
	}
}
