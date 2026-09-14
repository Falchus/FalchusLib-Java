package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketTitleWrapper extends PacketWrapper implements PacketTitle {
=======
class PacketTitleWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketTitleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
