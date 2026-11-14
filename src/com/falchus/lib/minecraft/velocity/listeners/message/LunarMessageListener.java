package com.falchus.lib.minecraft.velocity.listeners.message;

import com.falchus.lib.minecraft.utils.client.lunar.LunarMessage;
import com.falchus.lib.minecraft.velocity.FalchusLibMinecraftVelocity;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChannelRegisterEvent;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

public class LunarMessageListener extends LunarMessage {
	
	private static final FalchusLibMinecraftVelocity plugin = FalchusLibMinecraftVelocity.getInstance();
	
	private static final MinecraftChannelIdentifier channel_lunarApollo = MinecraftChannelIdentifier.from(lunarApollo);
	private static final MinecraftChannelIdentifier channel_apolloJson = MinecraftChannelIdentifier.from(apolloJson);
	
	public LunarMessageListener() {
		plugin.getProxy().getChannelRegistrar().register(
			channel_lunarApollo,
			channel_apolloJson
		);
		
		plugin.getProxy().getEventManager().register(plugin, this);
	}

    @Subscribe
    public void onChannelRegister(PlayerChannelRegisterEvent event) {
        if (!event.getChannels().contains(channel_lunarApollo)) return;
        onRegister(event.getPlayer().getUniqueId(), null);
    }
}
