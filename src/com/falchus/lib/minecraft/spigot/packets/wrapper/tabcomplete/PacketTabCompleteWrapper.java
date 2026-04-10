package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketTabCompleteWrapper extends PacketWrapper {

	PacketTabCompleteWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
