package com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketRemoveEntityEffectWrapper extends PacketWrapper {
	
	Field entityId;

	PacketRemoveEntityEffectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		entityId = getFirstField(
			"entityId",
			"a"
		);
	}

	public int getEntityId() {
		return getFieldValue(entityId);
	}
	
	public void setEntityId(int entityId) {
		setField(this.entityId, entityId);
	}
}
