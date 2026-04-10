package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketUseEntityWrapper extends PacketWrapper {
	
	Field entityId;
	Field action;

	PacketUseEntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		entityId = getFirstField(
			"entityId",
			"a"
		);
		action = getField("action");
	}

	public int getEntityId() {
		return getFieldValue(entityId);
	}
	
	public void setEntityId(int entityId) {
		setField(this.entityId, entityId);
	}

	/**
	 * @return PacketPlayInUseEntity$EnumEntityUseAction
	 */
	public Object getAction() {
		return getFieldValue(action);
	}
	
	/**
	 * @param action: PacketPlayInUseEntity$EnumEntityUseAction
	 */
	public void setAction(Object action) {
		setField(this.action, action);
	}
}
