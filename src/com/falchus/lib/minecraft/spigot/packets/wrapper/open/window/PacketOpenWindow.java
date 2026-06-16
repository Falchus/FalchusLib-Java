package com.falchus.lib.minecraft.spigot.packets.wrapper.open.window;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

public interface PacketOpenWindow extends IPacketWrapper {

	int getContainerId();
	void setContainerId(int containerId);
	
	Component getTitle();
	void setTitle(Component title);
}
