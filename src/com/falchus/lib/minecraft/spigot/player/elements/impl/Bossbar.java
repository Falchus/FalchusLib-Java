package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;

public class Bossbar extends PlayerElement {

	private BiFunction<Integer, Player, String> messageSupplier;
	private BiFunction<Integer, Player, Double> progressSupplier;
	
	private Bossbar(@NonNull Player player) {
    	super(player);
    }
	
	public void send(@NonNull BiFunction<Integer, Player, String> message, @NonNull BiFunction<Integer, Player, Double> progress) {
        messageSupplier = message;
        progressSupplier = progress;
        
        updateRunnable = () -> {
        	String newMessage = messageSupplier.apply(frame, player);
        	double newProgress = progressSupplier.apply(frame, player);
        	
    		PlayerUtils.sendBossbar(player, newMessage, newProgress);
        };
        update();
	}
	
	public void send(@NonNull Supplier<String> message, @NonNull Supplier<Double> progress) {
		send(
			(frame, player) -> message.get(),
			(frame, player) -> progress.get()
		);
	}
	
	public void sendUpdating(long intervalTicks, @NonNull BiFunction<Integer, Player, String> message, @NonNull BiFunction<Integer, Player, Double> progress) {
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
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
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> message, @NonNull Supplier<Double> progress) {
		sendUpdating(intervalTicks,
			(frame, player) -> message.get(),
			(frame, player) -> progress.get()
		);
	}
	
	@Override
	public void remove() {
		super.remove();
		
		PlayerUtils.removeBossbar(player);
	}
	
	public void setProgress(double progress) {
		send(
			messageSupplier,
			(frame, player) -> progress
		);
	}
}
