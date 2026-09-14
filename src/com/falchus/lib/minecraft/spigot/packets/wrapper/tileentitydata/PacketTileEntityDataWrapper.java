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
<<<<<<< HEAD
class PacketTileEntityDataWrapper extends PacketWrapper implements PacketTileEntityData {
=======
class PacketTileEntityDataWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
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
	
<<<<<<< HEAD
	@Override
	public void setPos(BlockPosition pos) {
		setField(this.pos, pos.getHandle());
=======
	/**
	 * @param pos	BlockPosition
	 */
	public void setPos(Object pos) {
		setField(this.pos, pos);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
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
	
<<<<<<< HEAD
	@Override
	public void setTag(CompoundTag tag) {
		setField(this.tag, tag.getHandle());
=======
	/**
	 * @param tag	NBTTagCompound
	 */
	public void setTag(Object tag) {
		setField(this.tag, tag);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
