package com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketMultiBlockChangeWrapper extends PacketWrapper implements PacketMultiBlockChange {
=======
class PacketMultiBlockChangeWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketMultiBlockChangeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
