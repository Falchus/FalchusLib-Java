package com.falchus.lib.minecraft.spigot.events.player;

import org.bukkit.entity.Player;

import lombok.Getter;

/**
 * Called when a player receives a packet.
 */
@Getter
public class PlayerPacketInEvent extends PlayerPacketEvent {
	
	public PlayerPacketInEvent(boolean async, Player player, Object packet) {
		super(async, player, packet);
	}
}
