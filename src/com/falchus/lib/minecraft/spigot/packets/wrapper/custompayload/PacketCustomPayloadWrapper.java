package com.falchus.lib.minecraft.spigot.packets.wrapper.custompayload;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketCustomPayloadWrapper extends PacketWrapper implements PacketCustomPayload {
=======
class PacketCustomPayloadWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketCustomPayloadWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
