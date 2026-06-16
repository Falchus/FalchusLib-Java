package com.falchus.lib.minecraft.spigot.packets.wrapper.block.place;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.block.Block;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketBlockPlaceWrapper extends PacketWrapper implements PacketBlockPlace {
	
	Field pos;
	Field type;
	Field data;
	Field block;
	
	PacketBlockPlaceWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		pos = getFirstField(
			"pos",
			"a"
		);
		type = getFirstField(
			"b0",
			"b"
		);
		data = getFirstField(
			"b1",
			"c"
		);
		block = getFirstField(
			"block",
			"d"
		);
	}

	@Override
	public BlockPosition getPos() {
		return SpigotWrapper.wrap(getFieldValue(pos));
	}
	
	@Override
	public void setPos(BlockPosition pos) {
		setField(this.pos, pos.getHandle());
	}

	@Override
	public int getType() {
		return getFieldValue(type);
	}
	
	@Override
	public void setType(int type) {
		setField(this.type, type);
	}

	@Override
	public int getData() {
		return getFieldValue(data);
	}
	
	@Override
	public void setData(int data) {
		setField(this.data, data);
	}

	@Override
	public Block getBlock() {
		return SpigotWrapper.wrap(getFieldValue(block));
	}
	
	@Override
	public void setBlock(Block block) {
		setField(this.block, block.getHandle());
	}
}
