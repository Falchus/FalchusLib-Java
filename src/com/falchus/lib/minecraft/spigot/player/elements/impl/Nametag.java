package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;

public class Nametag extends PlayerElement {
	
	private Supplier<String> prefixSupplier;
	private Supplier<String> suffixSupplier;
	private String lastPrefix = "";
	private String lastSuffix = "";
	
	private final String nameField = "a";
	private final String displayNameField = "b";
	private final String prefixField = "c";
	private final String suffixField = "d";
	private final String playersField = "g";
	private final String modeField = "h";

	private Object create;
	private Object update;
	private Object remove;
	
	private Nametag(@NonNull Player player) {
		super(player);
		if (ServerUtils.getMinorVersion() < 17) {
			Set<String> entries = new HashSet<>(List.of(player.getName()));
	
	        create = new NmsPacketBuilder(
	        	plugin.getVersionAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
	        	plugin.getVersionAdapter().getPackageNm() + "network.protocol.game.PacketPlayOutScoreboardTeam"
	        ).build();
	        ReflectionUtils.setDeclaredField(create, nameField, player.getName());
	        ReflectionUtils.setDeclaredField(create, displayNameField, player.getName());
	        ReflectionUtils.setDeclaredField(create, playersField, entries);
	        ReflectionUtils.setDeclaredField(create, modeField, 0);
	
	        update = new NmsPacketBuilder(
	        	plugin.getVersionAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
	        	plugin.getVersionAdapter().getPackageNm() + "network.protocol.game.PacketPlayOutScoreboardTeam"
	        ).build();
	        ReflectionUtils.setDeclaredField(update, nameField, player.getName());
	        ReflectionUtils.setDeclaredField(update, displayNameField, player.getName());
	        ReflectionUtils.setDeclaredField(update, playersField, entries);
	        ReflectionUtils.setDeclaredField(update, modeField, 2);
	        
	        remove = new NmsPacketBuilder(
	        	plugin.getVersionAdapter().getPackageNms() + "PacketPlayOutScoreboardTeam",
	        	plugin.getVersionAdapter().getPackageNm() + "network.protocol.game.PacketPlayOutScoreboardTeam"
	        ).build();
	        ReflectionUtils.setDeclaredField(remove, nameField, player.getName());
	        ReflectionUtils.setDeclaredField(remove, playersField, entries);
	        ReflectionUtils.setDeclaredField(remove, modeField, 4);
		}
	}

	/**
	 * Sets a one-time prefix and suffix.
	 */
	public void send(@NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix) {
		prefixSupplier = prefix;
		suffixSupplier = suffix;
		
		updateRunnable = () -> {
			String newPrefix = prefixSupplier.get();
			String newSuffix = suffixSupplier.get();
			
			if (ServerUtils.getMinorVersion() < 17) {
				if (!newPrefix.equals(lastPrefix)) {
			        ReflectionUtils.setDeclaredField(create, prefixField, newPrefix);
			        ReflectionUtils.setDeclaredField(update, prefixField, newPrefix);
			        lastPrefix = newPrefix;
				}
				if (!newSuffix.equals(lastSuffix)) {
			        ReflectionUtils.setDeclaredField(create, suffixField, newSuffix);
			        ReflectionUtils.setDeclaredField(update, suffixField, newSuffix);
			        lastSuffix = newSuffix;
				}
				
		        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
		        	PlayerUtils.sendPacket(onlinePlayer, create);
		        	PlayerUtils.sendPacket(onlinePlayer, update);
		        }
			}
		};
		update();
	}
	
	/**
	 * Updates prefix and suffiy periodically.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix) {
		super.sendUpdating(intervalTicks, () ->
			send(
				prefix,
				suffix
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		if (ServerUtils.getMinorVersion() < 17) {
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	PlayerUtils.sendPacket(onlinePlayer, remove);
	        }
		}
	}
}