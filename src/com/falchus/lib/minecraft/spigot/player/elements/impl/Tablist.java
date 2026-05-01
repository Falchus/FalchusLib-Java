package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Tablist extends PlayerElement {
	
	private BiFunction<Integer, Player, List<String>> headerSupplier;
	private BiFunction<Integer, Player, List<String>> footerSupplier;
	private Supplier<String> nameSupplier;
	
	private Tablist(@NonNull Player player) {
		super(player);
	}
	
	public void send(BiFunction<Integer, Player, List<String>> header, BiFunction<Integer, Player, List<String>> footer, Supplier<String> name) {
		headerSupplier = header;
		footerSupplier = footer;
		nameSupplier = name;
		
		updateRunnable = () -> {
			List<String> newHeader = headerSupplier != null ? headerSupplier.apply(frame, player) : null;
			List<String> newFooter = footerSupplier != null ? footerSupplier.apply(frame, player) : null;
			String newName = nameSupplier != null ? nameSupplier.get() : null;
			
			PlayerUtils.sendTablist(player, newHeader, newFooter, newName);
		};
		update();
	}
	
	public void send(Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name) {
		send(
			(frame, player) -> header.get(),
			(frame, player) -> footer.get(),
			name
		);
	}
	
	public void sendUpdating(long intervalTicks, BiFunction<Integer, Player, List<String>> header, BiFunction<Integer, Player, List<String>> footer, Supplier<String> name) {
		super.sendUpdating(intervalTicks, () ->
			send(
				header,
				footer,
				name
			)
		);
	}
	
	public void sendUpdating(long intervalTicks, Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name) {
		sendUpdating(intervalTicks,
			(frame, player) -> header.get(),
			(frame, player) -> footer.get(),
			name
		);
	}
	
	public void remove() {
		super.remove();
		
		send(
			(BiFunction<Integer, Player, List<String>>) null,
			(BiFunction<Integer, Player, List<String>>) null,
			(Supplier<String>) null
		);
	}
	
	public void setHeader(@NonNull List<String> header) {
		send(
			(frame, player) -> header,
			footerSupplier,
			nameSupplier
		);
	}
	
	public void setFooter(@NonNull List<String> footer) {
		send(
			headerSupplier,
			(frame, player) -> footer,
			nameSupplier
		);
	}
	
	public void setName(@NonNull String name) {
		send(
			headerSupplier,
			footerSupplier,
			() -> name
		);
	}
}
