package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.UUID;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketSpectateWrapper extends PacketWrapper implements PacketSpectate {
=======
class PacketSpectateWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field uuid;

	PacketSpectateWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		uuid = getFirstField(
			"uuid",
			"a"
		);
	}

	@Override
	public UUID getUUID() {
		return getFieldValue(uuid);
	}
	
	@Override
	public void setUUID(int uuid) {
		setField(this.uuid, uuid);
	}
}
