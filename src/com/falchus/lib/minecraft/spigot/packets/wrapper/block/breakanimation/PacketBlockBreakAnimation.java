package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;

public interface PacketBlockBreakAnimation extends PacketBlock {

	int getId();
	void setId(int id);
	
	int getProgress();
	void setProgress(int progress);
}
