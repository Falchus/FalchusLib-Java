package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketSteerVehicleWrapper extends PacketWrapper {

	PacketSteerVehicleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
