package com.falchus.lib.minecraft.velocity.listeners;

import com.falchus.lib.manager.EventManager;
import com.falchus.lib.minecraft.enums.Client;
import com.falchus.lib.minecraft.events.player.PlayerClientJoinEvent;
import com.falchus.lib.minecraft.velocity.FalchusLibMinecraftVelocity;
import com.velocitypowered.api.event.Subscribe;

import net.labymod.serverapi.server.velocity.event.LabyModPlayerJoinEvent;

public class JoinQuitListener {
	
	private static final FalchusLibMinecraftVelocity plugin = FalchusLibMinecraftVelocity.getInstance();
	
	public JoinQuitListener() {
		plugin.getProxy().getEventManager().register(plugin, this);
	}
	
	@Subscribe
	public void onPlayerLabyJoin(LabyModPlayerJoinEvent event) {
		EventManager.callEvent(new PlayerClientJoinEvent(event.labyModPlayer().getUniqueId(), Client.LABYMOD4));
	}
}
