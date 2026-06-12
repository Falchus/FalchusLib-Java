package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

class PacketPositionWrapper extends PacketWrapper {

	PacketPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
