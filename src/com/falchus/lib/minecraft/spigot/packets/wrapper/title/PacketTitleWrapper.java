package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketTitleWrapper extends PacketWrapper {

	PacketTitleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
