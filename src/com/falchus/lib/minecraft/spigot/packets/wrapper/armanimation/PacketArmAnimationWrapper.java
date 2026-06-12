package com.falchus.lib.minecraft.spigot.packets.wrapper.armanimation;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

class PacketArmAnimationWrapper extends PacketWrapper {

	PacketArmAnimationWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
