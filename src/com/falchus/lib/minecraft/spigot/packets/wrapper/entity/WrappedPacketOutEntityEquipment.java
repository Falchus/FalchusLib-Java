package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutEntityEquipment extends PacketEntityWrapper {
	
	WrappedPacketOutEntityEquipment(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityEquipment",
				networkProtocolGame + "PacketPlayOutEntityEquipment"
			)
		);
	}
}
