package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.util.Set;

import lombok.NonNull;

class PacketWindowItemsWrapper extends PacketWindowWrapper implements PacketWindowItems {

	PacketWindowItemsWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
