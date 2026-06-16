package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketSpawnEntityLivingWrapper extends PacketSpawnEntityWrapper implements PacketSpawnEntityLiving { // TODO

	PacketSpawnEntityLivingWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
