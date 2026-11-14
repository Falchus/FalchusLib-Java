package com.falchus.lib.minecraft.events.player;

import java.util.UUID;

import com.falchus.lib.events.Event;
import com.falchus.lib.minecraft.enums.Client;
import com.falchus.lib.minecraft.manager.ClientManager;

import lombok.Getter;
import lombok.NonNull;

/**
 * Called when a player's client has been identified after joining.
 */
@Getter
public class PlayerClientJoinEvent extends Event {
	
	private final UUID uuid;
	
	public PlayerClientJoinEvent(@NonNull UUID uuid, @NonNull Client client) {
		this.uuid = uuid;
		ClientManager.set(uuid, client);
	}
	
	public Client getClient() {
		return ClientManager.get(uuid);
	}
}
