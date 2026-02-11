package com.falchus.lib.minecraft.spigot.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Called when there's a event that should be cancelled in a Lobby.
 */
@Getter
@RequiredArgsConstructor
public class LobbyCancelEvent extends Event implements Cancellable {

	@Getter private static final HandlerList handlerList = new HandlerList();
	@Setter private boolean isCancelled;
	
	private final Event event;
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
}
