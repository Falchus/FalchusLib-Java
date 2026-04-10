package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketCloseWindowWrapper extends PacketWrapper {
	
	Field containerId;

	PacketCloseWindowWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		containerId = getFirstField(
			"containerId",
			"a"
		);
	}

	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}
}
