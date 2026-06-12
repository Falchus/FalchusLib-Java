package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketEnchantItemWrapper extends PacketWrapper {
	
	Field containerId;
	Field buttonId;

	PacketEnchantItemWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		containerId = getFirstField(
			"containerId",
			"a"
		);
		buttonId = getFirstField(
			"buttonId",
			"b"
		);
	}

	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}

	public int getButtonId() {
		return getFieldValue(buttonId);
	}
	
	public void setButtonId(int buttonId) {
		setField(this.buttonId, buttonId);
	}
}
