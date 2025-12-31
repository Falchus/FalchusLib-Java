package com.falchus.lib.minecraft.spigot.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;

/**
 * Called when there's a event that should be cancelled in a Lobby.
 */
@Getter
public class LobbyCancelEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	@Setter private boolean isCancelled;
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
