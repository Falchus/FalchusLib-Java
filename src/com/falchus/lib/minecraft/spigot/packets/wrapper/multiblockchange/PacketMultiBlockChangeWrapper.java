package com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketMultiBlockChangeWrapper extends PacketWrapper {

	PacketMultiBlockChangeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
