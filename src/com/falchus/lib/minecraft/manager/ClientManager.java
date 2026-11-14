package com.falchus.lib.minecraft.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.falchus.lib.minecraft.enums.Client;

import lombok.NonNull;

public class ClientManager {

	private static final Map<UUID, Client> clients = new HashMap<>();
	
	public static Client get(@NonNull UUID uuid) {
		return clients.getOrDefault(uuid, Client.OTHER);
	}
	
	public static void set(@NonNull UUID uuid, @NonNull Client client) {
		clients.put(uuid, client);
	}
}
