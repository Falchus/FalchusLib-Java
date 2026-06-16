package com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUpdateAttributesWrapper extends PacketWrapper implements PacketUpdateAttributes {
	
	Field entityId;

	PacketUpdateAttributesWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		entityId = getFirstField(
			"entityId",
			"a"
		);
	}

	@Override
	public int getEntityId() {
		return getFieldValue(entityId);
	}
	
	@Override
	public void setPos(int entityId) {
		setField(this.entityId, entityId);
	}
}
