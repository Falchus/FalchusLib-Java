package com.falchus.lib.minecraft.spigot.packets.wrapper.settings;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketSettingsWrapper extends PacketWrapper implements PacketSettings {
=======
class PacketSettingsWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketSettingsWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
