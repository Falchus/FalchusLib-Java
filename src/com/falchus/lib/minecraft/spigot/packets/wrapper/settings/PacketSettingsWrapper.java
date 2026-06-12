package com.falchus.lib.minecraft.spigot.packets.wrapper.settings;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

class PacketSettingsWrapper extends PacketWrapper {

	PacketSettingsWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
