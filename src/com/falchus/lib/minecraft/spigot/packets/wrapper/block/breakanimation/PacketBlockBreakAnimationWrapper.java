package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketBlockBreakAnimationWrapper extends PacketWrapper implements PacketBlockBreakAnimation {
=======
class PacketBlockBreakAnimationWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field id;
	Field pos;
	Field progress;
	
	PacketBlockBreakAnimationWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getFirstField(
			"id",
			"a"
		);
		pos = getFirstField(
			"pos",
			"b"
		);
		progress = getFirstField(
			"progress",
			"c"
		);
	}

	@Override
	public int getId() {
		return getFieldValue(id);
	}
	
	@Override
	public void setId(int id) {
		setField(this.id, id);
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
	public int getProgress() {
		return getFieldValue(progress);
	}
	
	@Override
	public void setProgress(int progress) {
		setField(this.progress, progress);
	}
}
