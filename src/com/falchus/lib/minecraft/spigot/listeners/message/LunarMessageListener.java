package com.falchus.lib.minecraft.spigot.listeners.message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.Messenger;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.utils.client.lunar.LunarMessage;

public class LunarMessageListener extends LunarMessage implements Listener {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public LunarMessageListener() {
		Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerIncomingPluginChannel(plugin, lunarApollo, (s, player, bytes) -> {});
        messenger.registerIncomingPluginChannel(plugin, apolloJson, (s, player, bytes) -> {});
        messenger.registerOutgoingPluginChannel(plugin, apolloJson);
 
        Bukkit.getPluginManager().registerEvents(this, plugin);
	}

    @EventHandler
    public void onRegisterChannel(PlayerRegisterChannelEvent event) {
        if (!event.getChannel().equalsIgnoreCase(lunarApollo)) return;
        Player player = event.getPlayer();

        onRegister(player.getUniqueId(), player.getWorld().getName());
    }
    
    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        // Sending the player's world name to the client is required for some modules
        updateWorld(player.getUniqueId(), player.getWorld().getName());
    }
}
