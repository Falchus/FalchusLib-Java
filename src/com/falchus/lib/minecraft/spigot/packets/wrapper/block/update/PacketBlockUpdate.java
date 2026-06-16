package com.falchus.lib.minecraft.spigot.packets.wrapper.block.update;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;

public interface PacketBlockUpdate extends PacketBlock {
	
	/**
	 * @return BlockState
	 */
	Object getBlockState();
	/**
	 * @param block	BlockState
	 */
	void setBlockState(Object blockState);
}
