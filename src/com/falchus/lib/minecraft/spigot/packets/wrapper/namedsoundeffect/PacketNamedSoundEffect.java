package com.falchus.lib.minecraft.spigot.packets.wrapper.namedsoundeffect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketNamedSoundEffect extends IPacketWrapper {

	int getX();
	void setX(int x);
	
	int getY();
	void setY(int y);
	
	int getZ();
	void setZ(int z);
	
	float getVolume();
	void setVolume(float volume);
}
