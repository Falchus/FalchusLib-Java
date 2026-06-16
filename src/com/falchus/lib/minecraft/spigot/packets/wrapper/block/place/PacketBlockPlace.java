package com.falchus.lib.minecraft.spigot.packets.wrapper.block.place;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.block.Block;

public interface PacketBlockPlace extends PacketBlock {
	
	int getType();
	void setType(int type);
	
	int getData();
	void setData(int data);
	
	Block getBlock();
	void setBlock(Block block);
}
