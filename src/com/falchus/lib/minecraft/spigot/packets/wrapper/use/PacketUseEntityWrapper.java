package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUseEntityWrapper extends PacketWrapper implements PacketUseEntity {
	
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

	@Override
	public int getEntityId() {
		return getFieldValue(entityId);
	}
	
	@Override
	public void setEntityId(int entityId) {
		setField(this.entityId, entityId);
	}

	@SneakyThrows
	@Override
	public Action getAction() {
		return Action.valueOf(getFieldValue(action, Enum.class).name());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
	}
}
