package com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketResourcePackStatusWrapper extends PacketWrapper implements PacketResourcePackStatus {
	
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setStatus(Status status) {
		setField(this.status, Enum.valueOf((Class<? extends Enum>) this.status.getType(), status.name()));
	}
}
