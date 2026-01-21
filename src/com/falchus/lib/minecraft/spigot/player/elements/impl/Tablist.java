package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Tablist extends PlayerElement {
	
	private Supplier<List<String>> headerSupplier;
	private Supplier<List<String>> footerSupplier;
	private Supplier<String> nameSupplier;
	
	private Tablist(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sends a custom header and footer.
	 */
	public void send(Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name) {
		headerSupplier = header;
		footerSupplier = footer;
		nameSupplier = name;
		
		updateRunnable = () -> {
			List<String> newHeader = headerSupplier != null ? headerSupplier.get() : null;
			List<String> newFooter = footerSupplier != null ? footerSupplier.get() : null;
			String newName = nameSupplier != null ? nameSupplier.get() : null;
			
			PlayerUtils.sendTablist(player, newHeader, newFooter, newName);
		};
		update();
	}
	
	/**
	 * Updates the tablist periodically with dynamic content.
	 */
	public void sendUpdating(long intervalTicks, Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name) {
		super.sendUpdating(intervalTicks, () ->
			send(
				header,
				footer,
				name
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		send(null, null, null);
	}
}
