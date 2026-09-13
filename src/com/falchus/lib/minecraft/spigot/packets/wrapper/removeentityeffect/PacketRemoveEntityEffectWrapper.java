package com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketRemoveEntityEffectWrapper extends PacketWrapper implements PacketRemoveEntityEffect {
=======
class PacketRemoveEntityEffectWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field entityId;

	PacketRemoveEntityEffectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
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
	public void setEntityId(int entityId) {
		setField(this.entityId, entityId);
	}
}
