package com.falchus.lib.minecraft.spigot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
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
		Player player = event.getPlayer();
		
		PlayerElement.get(Actionbar.class, player).remove();
		PlayerElement.get(Bossbar.class, player).remove();
		PlayerElement.get(Chat.class, player).remove();
		PlayerElement.get(Nametag.class, player).remove();
		PlayerElement.get(Scoreboard.class, player).remove();
		PlayerElement.get(Tablist.class, player).remove();
		
    	PlayerElement.updateAll(Actionbar.class);
    	PlayerElement.updateAll(Bossbar.class);
    	PlayerElement.updateAll(Chat.class);
    	PlayerElement.updateAll(Nametag.class);
		PlayerElement.updateAll(Scoreboard.class);
    	PlayerElement.updateAll(Tablist.class);
	}
}
