package com.falchus.lib.minecraft.spigot.packets.wrapper.custompayload;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketCustomPayloadWrapper extends PacketWrapper {

	PacketCustomPayloadWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
