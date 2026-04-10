package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInSteerVehicle extends PacketSteerVehicleWrapper {

    public WrappedPacketInSteerVehicle(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInSteerVehicle",
                        networkProtocolGame + "PacketPlayInSteerVehicle"
                )
        );
    }
}
