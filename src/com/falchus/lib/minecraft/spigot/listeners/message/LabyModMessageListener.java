package com.falchus.lib.minecraft.spigot.listeners.message;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.utils.client.labymod.LabyModMessage;

public class LabyModMessageListener extends LabyModMessage implements PluginMessageListener {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public LabyModMessageListener() {
		Messenger messenger = plugin.getServer().getMessenger();
		messenger.registerIncomingPluginChannel(plugin, labymod3Main, this);
		messenger.registerOutgoingPluginChannel(plugin, labymod3Main);
		messenger.registerOutgoingPluginChannel(plugin, damageindicator);
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals(labymod3Main)) return;
		handle(player.getUniqueId(), message);
	}
}
