package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketBlockDigWrapper extends PacketWrapper {
	
	Field pos;
	Field direction;
	Field action;
	
	PacketBlockDigWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		pos = getFirstField(
			"pos",
			"a"
		);
		direction = getFirstField(
			"direction",
			"b"
		);
		action = getFirstField(
			"action",
			"c"
		);
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

	/**
	 * @return EnumDirection
	 */
	public Object getDirection() {
		return getFieldValue(direction);
	}
	
	/**
	 * @param pos: EnumDirection
	 */
	public void setDirection(Object direction) {
		setField(this.direction, direction);
	}

	/**
	 * @return EnumPlayerDigType
	 */
	public Object getAction() {
		return getFieldValue(action);
	}
	
	/**
	 * @param pos: EnumPlayerDigType
	 */
	public void setAction(Object action) {
		setField(this.action, action);
	}
}
