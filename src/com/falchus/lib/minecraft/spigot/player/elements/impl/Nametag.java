package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Nametag extends PlayerElement {
	
	private Supplier<String> prefixSupplier;
	private Supplier<String> suffixSupplier;
	private Supplier<Integer> sortSupplier;
	
	private Nametag(@NonNull Player player) {
		super(player, true);
	}

	public void send(@NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix, @NonNull Supplier<Integer> sort) {
		prefixSupplier = prefix;
		suffixSupplier = suffix;
		sortSupplier = sort;
		
		updateRunnable = () -> {
			String newPrefix = prefixSupplier.get();
			String newSuffix = suffixSupplier.get();
			int newSort = sortSupplier.get();
			
	        PlayerUtils.sendNametag(player, newPrefix, newSuffix, newSort);
		};
		update();
	}
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix, @NonNull Supplier<String> suffix, @NonNull Supplier<Integer> sort) {
		super.sendUpdating(intervalTicks, () ->
			send(
				prefix,
				suffix,
				sort
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		PlayerUtils.removeNametag(player);
	}
	
	public void setPrefix(String prefix) {
		send(
			() -> prefix,
			suffixSupplier,
			sortSupplier
		);
	}
	
	public void setSuffix(String suffix) {
		send(
			prefixSupplier,
			() -> suffix,
			sortSupplier
		);
	}
	
	public void setSort(int sort) {
		send(
			prefixSupplier,
			suffixSupplier,
			() -> sort
		);
	}
}