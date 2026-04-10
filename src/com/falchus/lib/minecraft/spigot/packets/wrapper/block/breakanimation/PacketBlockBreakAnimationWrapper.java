package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketBlockBreakAnimationWrapper extends PacketWrapper {
	
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

	public int getId() {
		return getFieldValue(id);
	}
	
	public void setId(int id) {
		setField(this.id, id);
	}

	/**
	 * @return BlockPosition
	 */
	public Object getPos() {
		return getFieldValue(pos);
	}
	
	/**
	 * @param pos: BlockPosition
	 */
	public void setPos(Object pos) {
		setField(this.pos, pos);
	}

	public int getProgress() {
		return getFieldValue(progress);
	}
	
	public void setProgress(int progress) {
		setField(this.progress, progress);
	}
}
