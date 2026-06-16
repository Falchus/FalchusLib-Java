package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

public interface PacketWindowData extends PacketWindow {

	int getId();
	void setId(int id);
	
	int getValue();
	void setValue(int value);
}
