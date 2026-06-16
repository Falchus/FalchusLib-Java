package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketSpawnEntityWrapper extends PacketWrapper implements PacketSpawnEntity {

	Field id;

	PacketSpawnEntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);

		id = getFirstField(
			"id",
			"a"
		);
	}

	@Override
	public int getId() {
		return getFieldValue(id);
	}

	@Override
	public void setId(int id) {
		setField(this.id, id);
	}
}
