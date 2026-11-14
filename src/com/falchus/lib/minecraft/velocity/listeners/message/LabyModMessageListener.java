package com.falchus.lib.minecraft.velocity.listeners.message;

import com.falchus.lib.minecraft.utils.client.labymod.LabyModMessage;
import com.falchus.lib.minecraft.velocity.FalchusLibMinecraftVelocity;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

public class LabyModMessageListener extends LabyModMessage {
	
	private static final FalchusLibMinecraftVelocity plugin = FalchusLibMinecraftVelocity.getInstance();
	
	private static final MinecraftChannelIdentifier channel_labymod3Main = MinecraftChannelIdentifier.from(labymod3Main);
	private static final LegacyChannelIdentifier channel_damageindicator = new LegacyChannelIdentifier(damageindicator);
	
	public LabyModMessageListener() {
		plugin.getProxy().getChannelRegistrar().register(channel_labymod3Main);
		plugin.getProxy().getChannelRegistrar().register(channel_damageindicator);
		
		plugin.getProxy().getEventManager().register(plugin, this);
	}

	@Subscribe
	public void onPluginMessage(PluginMessageEvent event) {
		if (!event.getIdentifier().equals(channel_labymod3Main)) return;
		if (!(event.getSource() instanceof Player player)) return;
		handle(player.getUniqueId(), event.getData());
	}
}
