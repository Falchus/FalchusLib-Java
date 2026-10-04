package com.falchus.lib.minecraft.spigot.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.enums.Client;

public class ClientManager {

	private static final Map<UUID, Client> clients = new HashMap<>();
	
	public static Client get(Player player) {
		return clients.getOrDefault(player.getUniqueId(), Client.OTHER);
	}
	
	public void set(Player player, Client client) {
		clients.put(player.getUniqueId(), client);
	}
}
