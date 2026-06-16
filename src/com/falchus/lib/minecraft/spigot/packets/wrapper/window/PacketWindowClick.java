package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

public interface PacketWindowClick extends PacketWindow {

	int getSlotNum();
	void setSlotNum(int slotNum);
	
	int getButtonNum();
	void setButtonNum(int buttonNum);
	
	short getUID();
	void setUID(short uid);
}
