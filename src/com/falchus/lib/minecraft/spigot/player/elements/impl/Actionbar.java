package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Actionbar extends PlayerElement {
	
	private BiFunction<Integer, Player, String> messageSupplier;

	private Actionbar(@NonNull Player player) {
		super(player);
	}
	
	public void send(@NonNull BiFunction<Integer, Player, String> message) {
		messageSupplier = message;
		
		updateRunnable = () -> {
			String newMessage = messageSupplier.apply(frame, player);
			
			PlayerUtils.sendActionbar(player, newMessage);
		};
		update();
	}
	
	public void send(@NonNull Supplier<String> message) {
		send(
			(frame, player) -> message.get()
		);
	}
	
	public void sendUpdating(long intervalTicks, @NonNull BiFunction<Integer, Player, String> message) {
		super.sendUpdating(intervalTicks, () ->
			send(
				message
			)
		);
	}
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> message) {
		sendUpdating(intervalTicks,
			(frame, player) -> message.get()
		);
	}
}
