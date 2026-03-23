package com.falchus.lib.minecraft.spigot.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;

/**
 * Called when a player receives/sends a packet.
 */
@Getter
public class PlayerPacketEvent extends Event implements Cancellable {

	@Getter private static final HandlerList handlerList = new HandlerList();
	@Setter private boolean cancelled;
	
	private final Player player;
	private final Object packet;
	
	public PlayerPacketEvent(boolean async, Player player, Object packet) {
		super(async);
		this.player = player;
		this.packet = packet;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
}
