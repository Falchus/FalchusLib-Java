package com.falchus.lib.minecraft.spigot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
import com.falchus.lib.minecraft.spigot.player.elements.impl.Tag;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

public class JoinQuitListener implements Listener {

	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
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
    	PlayerElement.updateAll(Tag.class);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin_HIGH(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			if (PlayerUtils.vanished.contains(player.getUniqueId())) {
				onlinePlayer.hidePlayer(player);
			} else {
				onlinePlayer.showPlayer(player);
			}
			if (PlayerUtils.vanished.contains(onlinePlayer.getUniqueId())) {
				player.hidePlayer(onlinePlayer);
			} else {
				player.showPlayer(onlinePlayer);
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
    	PlayerElement.updateAll(Actionbar.class);
    	PlayerElement.updateAll(Bossbar.class);
    	PlayerElement.updateAll(Chat.class);
    	PlayerElement.updateAll(Nametag.class);
		PlayerElement.updateAll(Scoreboard.class);
    	PlayerElement.updateAll(Tablist.class);
    	PlayerElement.updateAll(Tag.class);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit_HIGHEST(PlayerQuitEvent event) {
		PacketInjector.uninject(event.getPlayer());
	}
}
