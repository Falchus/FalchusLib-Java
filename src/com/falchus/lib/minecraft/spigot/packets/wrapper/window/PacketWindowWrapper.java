package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketWindowWrapper extends PacketWrapper implements PacketWindow {
	
	Field containerId;

	PacketWindowWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		containerId = getFirstField(
			"containerId",
			"a"
		);
	}

	@Override
	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	@Override
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}
}
