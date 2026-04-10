package com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketResourcePackStatusWrapper extends PacketWrapper {
	
	Field status;

	PacketResourcePackStatusWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		status = getFirstField(
			"status",
			"action",
			"b"
		);
	}

	/**
	 * @return PacketPlayInResourcePackStatus$EnumResourcePackStatus
	 */
	public Object getStatus() {
		return getFieldValue(status);
	}
	
	/**
	 * @param status: PacketPlayInResourcePackStatus$EnumResourcePackStatus
	 */
	public void setStatus(Object status) {
		setField(this.status, status);
	}
}
