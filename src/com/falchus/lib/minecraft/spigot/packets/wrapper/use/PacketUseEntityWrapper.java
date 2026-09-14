package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
<<<<<<< HEAD
=======
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketUseEntityWrapper extends PacketWrapper implements PacketUseEntity {
=======
class PacketUseEntityWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
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
<<<<<<< HEAD
		return Action.valueOf(getFieldValue(action, Enum.class).name());
=======
		String name;
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			name = ((Enum<?>) getFieldValue(action)).name();
		} else {
			name = getFieldValue(action, Enum.class).name();
		}
		return Action.valueOf(name);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
=======
	/**
	 * @param action	PacketPlayInUseEntity$EnumEntityUseAction
	 */
	public void setAction(Object action) {
		setField(this.action, action);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
