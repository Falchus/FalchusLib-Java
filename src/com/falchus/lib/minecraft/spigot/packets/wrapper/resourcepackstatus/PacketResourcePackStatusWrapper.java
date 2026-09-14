package com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketResourcePackStatusWrapper extends PacketWrapper implements PacketResourcePackStatus {
=======
class PacketResourcePackStatusWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field status;

	PacketResourcePackStatusWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		status = getFirstField(
			"status",
			"action",
			"b"
		);
	}

	@Override
	public Status getStatus() {
		return Status.valueOf(getFieldValue(status, Enum.class).name());
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setStatus(Status status) {
		setField(this.status, Enum.valueOf((Class<? extends Enum>) this.status.getType(), status.name()));
=======
	/**
	 * @param status	PacketPlayInResourcePackStatus$EnumResourcePackStatus
	 */
	public void setStatus(Object status) {
		setField(this.status, status);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
