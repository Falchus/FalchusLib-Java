package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketTabCompleteWrapper extends PacketWrapper implements PacketTabComplete {
=======
class PacketTabCompleteWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketTabCompleteWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
