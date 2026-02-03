package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
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
		Set<String> entries = new HashSet<>(Collections.singletonList(player.getName()));

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
	public void send(@NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix) {
		prefixSupplier = prefix;
		suffixSupplier = suffix;
		
		updateRunnable = () -> {
			String newPrefix = prefixSupplier.get();
			String newSuffix = suffixSupplier.get();
			
			if (!newPrefix.equals(lastPrefix)) {
		        ReflectionUtils.setField(create, prefixField, newPrefix);
		        ReflectionUtils.setField(update, prefixField, newPrefix);
		        lastPrefix = newPrefix;
			}
			if (!newSuffix.equals(lastSuffix)) {
		        ReflectionUtils.setField(create, suffixField, newSuffix);
		        ReflectionUtils.setField(update, suffixField, newSuffix);
		        lastSuffix = newSuffix;
			}
			
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	PlayerUtils.sendPacket(onlinePlayer, create);
	        	PlayerUtils.sendPacket(onlinePlayer, update);
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
		
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        	PlayerUtils.sendPacket(onlinePlayer, remove);
        }
	}
}