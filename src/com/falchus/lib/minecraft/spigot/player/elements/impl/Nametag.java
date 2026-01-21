package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;

public class Nametag extends PlayerElement {
	
	private final Scoreboard scoreboard;
	private Team team;
	
	private final String nameField = "a";
	private final String displayNameField = "b";
	private final String prefixField = "c";
	private final String suffixField = "d";
	private final String playersField = "g";
	private final String modeField = "h";

	private Object create;
	private Object update;
	private Object remove;
	
	private String lastPrefix = "";
	private String lastSuffix = "";
	
	private Nametag(@NonNull Player player) {
		super(player);
		scoreboard = player.getScoreboard() != null
				? player.getScoreboard()
				: Bukkit.getScoreboardManager().getNewScoreboard();
		
		Team team = scoreboard.getTeam(player.getName());
		if (team == null) {
			team = scoreboard.registerNewTeam(player.getName());
		}
		if (!team.hasEntry(player.getName())) {
			team.addEntry(player.getName());
		}
		this.team = team;
		
		player.setScoreboard(scoreboard);
		
		HashSet<String> entries = new HashSet<>(Collections.singletonList(player.getName()));

        create = new NmsPacketBuilder(
        	plugin.getNmsAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
        	plugin.getNmsAdapter().getPackageNm() + "protocol.game.PacketPlayOutScoreboardTeam"
        ).build();
        ReflectionUtils.setField(create, nameField, player.getName());
        ReflectionUtils.setField(create, displayNameField, player.getName());
        ReflectionUtils.setField(create, playersField, entries);
        ReflectionUtils.setField(create, modeField, 0);

        update = new NmsPacketBuilder(
            	plugin.getNmsAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
            	plugin.getNmsAdapter().getPackageNm() + "protocol.game.PacketPlayOutScoreboardTeam"
            ).build();
        ReflectionUtils.setField(update, nameField, player.getName());
        ReflectionUtils.setField(update, displayNameField, player.getName());
        ReflectionUtils.setField(update, playersField, entries);
        ReflectionUtils.setField(update, modeField, 2);
        
        remove = new NmsPacketBuilder(
        	plugin.getNmsAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
        	plugin.getNmsAdapter().getPackageNm() + "protocol.game.PacketPlayOutScoreboardTeam"
        ).build();
        ReflectionUtils.setField(remove, nameField, player.getName());
        ReflectionUtils.setField(remove, playersField, entries);
        ReflectionUtils.setField(remove, modeField, 4);
	}

	/**
	 * Sets a one-time prefix and suffix.
	 */
	public void send(@NonNull String prefix, @NonNull String suffix) {
		if (!prefix.equals(lastPrefix)) {
	        ReflectionUtils.setField(create, prefixField, prefix);
	        ReflectionUtils.setField(update, prefixField, prefix);
	        lastPrefix = prefix;
		}
		if (!suffix.equals(lastSuffix)) {
	        ReflectionUtils.setField(create, suffixField, suffix);
	        ReflectionUtils.setField(update, suffixField, suffix);
	        lastSuffix = suffix;
		}
        
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        	PlayerUtils.sendPacket(onlinePlayer, create);
        	PlayerUtils.sendPacket(onlinePlayer, update);
        }
	}
	
	/**
	 * Updates prefix and suffiy periodically.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix) {
		super.sendUpdating(intervalTicks, () ->
			send(
				prefix.get(),
				suffix.get()
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		if (team.hasEntry(player.getName())) {
			team.removeEntry(player.getName());
		}
		
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        	PlayerUtils.sendPacket(onlinePlayer, remove);
        }
	}
}