package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketCloseWindow extends IPacketWrapper {

	int getContainerId();
	void setContainerId(int containerId);
}
