package com.falchus.lib.minecraft.spigot.packets.wrapper.tileentitydata;

import com.falchus.lib.minecraft.spigot.packets.wrapper.block.PacketBlock;
import com.falchus.lib.minecraft.spigot.wrapper.nbt.CompoundTag;

public interface PacketTileEntityData extends PacketBlock {
	
	int getType();
	void setType(int type);
	
	CompoundTag getTag();
	void setTag(CompoundTag tag);
}
