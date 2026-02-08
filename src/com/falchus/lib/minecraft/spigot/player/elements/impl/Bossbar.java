package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;

public class Bossbar extends PlayerElement {

	private Supplier<String> messageSupplier;
	private Supplier<Double> progressSupplier;
	
	private Bossbar(@NonNull Player player) {
    	super(player);
    }
	
	/**
	 * Sends a one-time Bossbar message.
	 */
	public void send(@NonNull Supplier<String> message, @NonNull Supplier<Double> progress) {
        messageSupplier = message;
        progressSupplier = progress;
        
        updateRunnable = () -> {
        	String newMessage = messageSupplier.get();
        	Double newProgress = progressSupplier.get();
        	
    		PlayerUtils.sendBossbar(player, newMessage, newProgress);
        };
        update();
	}
	
	/**
	 * Sends a Bossbar message repeatedly at a fixed interval.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> message, @NonNull Supplier<Double> progress) {
		if (ServerUtils.getMinorVersion() < 17) {
			super.sendUpdating(intervalTicks, () ->
				send(
					message,
					progress
				)
			);
		} else {
			send(
				message,
				progress
			);
		}
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
		send(
			messageSupplier,
			() -> progress
		);
	}
}
