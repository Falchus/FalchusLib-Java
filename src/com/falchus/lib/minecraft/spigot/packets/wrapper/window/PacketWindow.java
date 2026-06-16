package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketWindow extends IPacketWrapper {

	int getContainerId();
	void setContainerId(int containerId);
}
