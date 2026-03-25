package com.falchus.lib.minecraft.spigot.events.player;

import org.bukkit.entity.Player;

import lombok.Getter;

/**
 * Called when a player sends a packet.
 */
@Getter
public class PlayerPacketOutEvent extends PlayerPacketEvent {
	
	public PlayerPacketOutEvent(boolean async, Player player, Object packet) {
		super(async, player, packet);
	}
}
