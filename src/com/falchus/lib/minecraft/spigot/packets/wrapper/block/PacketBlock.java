package com.falchus.lib.minecraft.spigot.packets.wrapper.block;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;

public interface PacketBlock extends IPacketWrapper {

	BlockPosition getPos();
	void setPos(BlockPosition pos);
}
