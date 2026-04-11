package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Nametag extends PlayerElement {
	
	private Supplier<String> prefixSupplier;
	private Supplier<String> suffixSupplier;
	
	private Nametag(@NonNull Player player) {
		super(player);
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
			
	        PlayerUtils.sendNametag(player, newPrefix, newSuffix);
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
		
		PlayerUtils.removeNametag(player);
	}
}