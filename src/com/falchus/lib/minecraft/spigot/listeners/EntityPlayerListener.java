package com.falchus.lib.minecraft.spigot.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

public class EntityPlayerListener implements Listener {

	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	public final Map<UUID, Object> players = new HashMap<>();
	public final Map<UUID, Consumer<Player>> actions = new HashMap<>();
	
	public EntityPlayerListener() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player player)) return;
		UUID uuid = player.getUniqueId();
		
		if (players.containsKey(uuid)) {
			Consumer<Player> action = actions.get(uuid);
			if (action != null) {
				event.setCancelled(true);
				action.accept(event.getPlayer());
			}
		}
	}
}
