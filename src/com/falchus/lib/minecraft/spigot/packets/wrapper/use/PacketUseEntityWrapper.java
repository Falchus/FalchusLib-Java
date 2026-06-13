package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUseEntityWrapper extends PacketWrapper {
	
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
	
	public enum Action {
		INTERACT,
		ATTACK,
		INTERACT_AT
	}

	@SneakyThrows
	public Action getAction() {
		String name;
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			name = ((Enum<?>) getFieldValue(action)).name();
		} else {
			name = getFieldValue(action, Enum.class).name();
		}
		return Action.valueOf(name);
	}
	
	/**
	 * @param action	PacketPlayInUseEntity$EnumEntityUseAction
	 */
	public void setAction(Object action) {
		setField(this.action, action);
	}
}
