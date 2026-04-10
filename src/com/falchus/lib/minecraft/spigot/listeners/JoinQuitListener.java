package com.falchus.lib.minecraft.spigot.listeners;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.packets.PacketInjector;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.player.elements.impl.*;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class JoinQuitListener implements Listener {

    private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

    public JoinQuitListener() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin_LOWEST(@NotNull PlayerJoinEvent event) {
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

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin_HIGH(@NotNull PlayerJoinEvent event) {
        Player joining = event.getPlayer();

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (PlayerUtils.vanished.contains(joining.getUniqueId())) {
                online.hidePlayer(plugin, joining);
            } else {
                online.showPlayer(plugin, joining);
            }

            if (PlayerUtils.vanished.contains(online.getUniqueId())) {
                joining.hidePlayer(plugin, online);
            } else {
                joining.showPlayer(plugin, online);
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
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit_HIGHEST(@NotNull PlayerQuitEvent event) {
        PacketInjector.uninject(event.getPlayer());
    }
}