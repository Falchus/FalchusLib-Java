package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
<<<<<<< HEAD
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
=======
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketBlockDigWrapper extends PacketWrapper implements PacketBlockDig {
=======
class PacketBlockDigWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
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
	public Direction getDirection() {
		return Direction.valueOf(getFieldValue(direction, Enum.class).name());
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setDirection(Direction direction) {
		setField(this.direction, Enum.valueOf((Class<? extends Enum>) this.direction.getType(), direction.name()));
=======
	/**
	 * @param direction	EnumDirection
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
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}

	@SneakyThrows
	@Override
	public Action getAction() {
<<<<<<< HEAD
		return Action.valueOf(getFieldValue(action, Enum.class).name());
=======
		String name;
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			name = ((Enum<?>) getFieldValue(action)).name();
		} else {
			name = getFieldValue(action, Enum.class).name();
		}
		return Action.valueOf(name);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
=======
	/**
	 * @param action	EnumPlayerDigType
	 */
	public void setAction(Object action) {
		setField(this.action, action);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
