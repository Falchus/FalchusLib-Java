package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketSteerVehicleWrapper extends PacketWrapper {

    PacketSteerVehicleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
