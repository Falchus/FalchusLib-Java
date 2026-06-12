package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.position;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

class PacketSpawnPositionWrapper extends PacketWrapper {

	PacketSpawnPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
