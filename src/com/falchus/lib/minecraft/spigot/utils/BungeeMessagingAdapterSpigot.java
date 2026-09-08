package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.utils.messaging.BungeeMessaging;
import com.falchus.lib.minecraft.utils.messaging.BungeeMessagingAdapter;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import lombok.NonNull;

public class BungeeMessagingAdapterSpigot implements BungeeMessagingAdapter, Listener, PluginMessageListener {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	private static boolean available;
	private static boolean checking;
	
	public BungeeMessagingAdapterSpigot() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, BungeeMessaging.channel, this);
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, BungeeMessaging.channel);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (available || checking) return;
		checking = true;
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Ping");
		event.getPlayer().sendPluginMessage(plugin, BungeeMessaging.channel, out.toByteArray());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (Bukkit.getOnlinePlayers().size() <= 1) {
			available = false;
			checking = false;
		}
	}
	
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals(BungeeMessaging.channel)) return;
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		if (!in.readUTF().equals("Pong")) return;
		available = true;
		checking = false;
	}
	
	@Override
	public boolean isAvailable() {
		return available;
	}

	@Override
	public void execute(@NonNull String command) {
		if (!available) return;
		
		Player player = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
		if (player == null) return;
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Execute");
		out.writeUTF(command);
		player.sendPluginMessage(plugin, BungeeMessaging.channel, out.toByteArray());
	}
	
	@Override
	public void connect(@NonNull String name, @NonNull String server) {
		Player player = Bukkit.getPlayerExact(name);
		if (player == null) return;
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
