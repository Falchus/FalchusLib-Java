package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;

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
			
			if (ServerUtils.getMinorVersion() < 12) {
				try {
					Object chatMessage = plugin.getNmsAdapter().createChatComponentText(newMessage);
					Object packet = new NmsPacketBuilder(plugin.getNmsAdapter().getPackageNms() + "PacketPlayOutChat")
							.withArgs(chatMessage, (byte) 2)
							.build();
					PlayerUtils.sendPacket(player, packet);
				} catch (Exception e) {
			        throw new RuntimeException(e);
			    }
			}
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
