package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;

public interface PacketWorldEvent extends PacketBlock {

	int getType();
	void setType(int type);
	
	int getData();
	void setData(int data);
	
	boolean isGlobalEvent();
	void setGlobalEvent(boolean globalEvent);
}
