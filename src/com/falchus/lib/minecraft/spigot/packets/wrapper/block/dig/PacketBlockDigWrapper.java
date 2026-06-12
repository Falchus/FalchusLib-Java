package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketBlockDigWrapper extends PacketWrapper {
	
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
	
	public enum Action {
		START_DESTROY_BLOCK,
		ABORT_DESTROY_BLOCK,
		STOP_DESTROY_BLOCK,
		DROP_ALL_ITEMS,
		DROP_ITEM,
		RELEASE_USE_ITEM
	}

	@SneakyThrows
	public Action getAction() {
		String name;
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			name = ((Enum<?>) ReflectionUtils.getMethod(getFieldValue(action), "getType").invoke(getFieldValue(action))).name();
		} else {
			name = getFieldValue(action, Enum.class).name();
		}
		return Action.valueOf(name);
	}
	
	/**
	 * @param pos: EnumPlayerDigType
	 */
	public void setAction(Object action) {
		setField(this.action, action);
	}
}
