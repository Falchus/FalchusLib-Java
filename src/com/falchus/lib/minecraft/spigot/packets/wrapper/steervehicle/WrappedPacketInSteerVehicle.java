package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInSteerVehicle extends PacketSteerVehicleWrapper {

	public WrappedPacketInSteerVehicle(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInSteerVehicle",
				networkProtocolGame + "PacketPlayInSteerVehicle"
			)
		);
	}
}
