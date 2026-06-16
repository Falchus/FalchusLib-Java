package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketEnchantItem extends IPacketWrapper {

	int getContainerId();
	void setContainerId(int containerId);
	
	int getButtonId();
	void setButtonId(int buttonId);
}
