package com.falchus.lib.minecraft.spigot.packets.wrapper.tileentitydata;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
import com.falchus.lib.minecraft.spigot.wrapper.nbt.CompoundTag;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketTileEntityDataWrapper extends PacketWrapper implements PacketTileEntityData {
	
	Field pos;
	Field type;
	Field tag;

	PacketTileEntityDataWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		pos = getFirstField(
			"pos",
			"a"
		);
		type = getFirstField(
			"type",
			"b"
		);
		tag = getFirstField(
			"tag",
			"c"
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
	public CompoundTag getTag() {
		return SpigotWrapper.wrap(getFieldValue(tag));
	}
	
	@Override
	public void setTag(CompoundTag tag) {
		setField(this.tag, tag.getHandle());
	}
}
