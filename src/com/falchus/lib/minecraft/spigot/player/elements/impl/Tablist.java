package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Tablist extends PlayerElement {
	
	private Tablist(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sends a custom header and footer.
	 */
	public void send(List<String> header, List<String> footer, String name) {
		PlayerUtils.sendTablist(player, header, footer, name);
	}
	
	/**
	 * Updates the tablist periodically with dynamic content.
	 */
	public void sendUpdating(long intervalTicks, Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name) {
		super.sendUpdating(intervalTicks, () ->
			send(
				header != null ? header.get() : null,
				footer != null ? footer.get() : null,
				name != null ? name.get() : null
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		send(null, null, null);
	}
}
