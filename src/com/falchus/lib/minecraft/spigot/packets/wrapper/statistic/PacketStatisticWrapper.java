package com.falchus.lib.minecraft.spigot.packets.wrapper.statistic;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketStatisticWrapper extends PacketWrapper {

	PacketStatisticWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
