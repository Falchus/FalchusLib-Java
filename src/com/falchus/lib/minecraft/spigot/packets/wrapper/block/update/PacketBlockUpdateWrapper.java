package com.falchus.lib.minecraft.spigot.packets.wrapper.block.update;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketBlockUpdateWrapper extends PacketWrapper implements PacketBlockUpdate {
	
	Field pos;
	Field blockState;
	
	PacketBlockUpdateWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		pos = getFirstField(
			"pos",
			"a"
		);
		blockState = getFirstField(
			"blockState",
			"b"
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
	public Object getBlockState() {
		return getFieldValue(blockState);
	}
	
	@Override
	public void setBlockState(Object blockState) {
		setField(this.blockState, blockState);
	}
}
