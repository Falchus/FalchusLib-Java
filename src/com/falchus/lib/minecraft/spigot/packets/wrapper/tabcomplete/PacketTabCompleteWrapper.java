package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

class PacketTabCompleteWrapper extends PacketWrapper implements PacketTabComplete {

	PacketTabCompleteWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
