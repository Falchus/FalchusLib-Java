package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Bossbar extends PlayerElement {

	private String message;
    
	private Bossbar(@NonNull Player player) {
    	super(player);
    }
	
	/**
	 * Sends a one-time Bossbar message.
	 */
	public void send(@NonNull String message, double progress) {
		this.message = message;
		
		PlayerUtils.sendBossbar(player, message, progress);
	}
	
	/**
	 * Sends a Bossbar message repeatedly at a fixed interval.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> messageSupplier, @NonNull Supplier<Double> progressSupplier) {
		super.sendUpdating(intervalTicks, () -> {
			String message = messageSupplier.get();
			double progress = progressSupplier.get();
			send(message, progress);
		});
	}
	
	/**
	 * Removes the Bossbar, cancelling any ongoing update tasks.
	 */
	@Override
	public void remove() {
		super.remove();
		PlayerUtils.removeBossbar(player);
	}
	
	/**
	 * Sets the health/progress of the Bossbar.
	 */
	public void setProgress(double progress) {
		send(message, progress);
	}
}
