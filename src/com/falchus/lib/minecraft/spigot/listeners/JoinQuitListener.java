package com.falchus.lib.minecraft.spigot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.packets.PacketInjector;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Actionbar;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Bossbar;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Chat;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Nametag;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Scoreboard;
import com.falchus.lib.minecraft.spigot.player.elements.impl.Tablist;

public class JoinQuitListener implements Listener {

	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public JoinQuitListener() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin_LOWEST(PlayerJoinEvent event) {
		PacketInjector.inject(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
    	PlayerElement.updateAll(Actionbar.class);
    	PlayerElement.updateAll(Bossbar.class);
    	PlayerElement.updateAll(Chat.class);
    	PlayerElement.updateAll(Nametag.class);
		PlayerElement.updateAll(Scoreboard.class);
    	PlayerElement.updateAll(Tablist.class);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
    	PlayerElement.updateAll(Actionbar.class);
    	PlayerElement.updateAll(Bossbar.class);
    	PlayerElement.updateAll(Chat.class);
    	PlayerElement.updateAll(Nametag.class);
		PlayerElement.updateAll(Scoreboard.class);
    	PlayerElement.updateAll(Tablist.class);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit_HIGHEST(PlayerQuitEvent event) {
		PacketInjector.uninject(event.getPlayer());
	}
}
