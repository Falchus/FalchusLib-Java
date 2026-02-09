package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Actionbar extends PlayerElement {
	
	private Supplier<String> messageSupplier;

	private Actionbar(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sends a one-time action bar message.
	 */
	public void send(@NonNull Supplier<String> message) {
		messageSupplier = message;
		
		updateRunnable = () -> {
			String newMessage = messageSupplier.get();
			
			PlayerUtils.sendActionbar(player, newMessage);
		};
		update();
	}
	
	/**
	 * Sends an action bar message repeatedly at a fixed interval.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> message) {
		super.sendUpdating(intervalTicks, () ->
			send(
				message
			)
		);
	}
}
