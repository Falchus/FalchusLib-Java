package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import java.util.Set;

import lombok.NonNull;

abstract class PacketUpdateWindowItems extends PacketUpdateWindow {

	PacketUpdateWindowItems(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
