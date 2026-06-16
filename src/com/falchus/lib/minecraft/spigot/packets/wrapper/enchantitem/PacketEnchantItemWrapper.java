package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketEnchantItemWrapper extends PacketWrapper implements PacketEnchantItem {
	
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

	@Override
	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	@Override
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}

	@Override
	public int getButtonId() {
		return getFieldValue(buttonId);
	}
	
	@Override
	public void setButtonId(int buttonId) {
		setField(this.buttonId, buttonId);
	}
}
