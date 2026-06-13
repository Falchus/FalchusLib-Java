package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import java.util.Set;

import lombok.NonNull;

// TODO: add all entity spawn packets
public class WrappedPacketOutSpawnEntity extends PacketSpawnEntityWrapper {

	private WrappedPacketOutSpawnEntity(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutSpawnEntity",
			networkProtocolGame + "PacketPlayOutSpawnEntity"
		));
	}
}
