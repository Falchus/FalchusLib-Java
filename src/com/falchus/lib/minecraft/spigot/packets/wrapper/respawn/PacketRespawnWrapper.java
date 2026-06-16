package com.falchus.lib.minecraft.spigot.packets.wrapper.respawn;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketRespawnWrapper extends PacketWrapper implements PacketRespawn { // TODO

	PacketRespawnWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
