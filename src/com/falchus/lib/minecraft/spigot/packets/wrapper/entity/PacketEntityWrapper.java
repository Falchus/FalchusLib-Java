package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketEntityWrapper extends PacketWrapper implements PacketEntity {
=======
class PacketEntityWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	Field entityId;
	
	PacketEntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		entityId = getFirstField(
			"entityId",
			"entity",
			"id",
			"a"
		);
	}

	@Override
	public int getEntityId() {
		return getFieldValue(entityId);
	}
	
	@Override
	public void setEntityId(int entityId) {
		setField(this.entityId, entityId);
	}
}
