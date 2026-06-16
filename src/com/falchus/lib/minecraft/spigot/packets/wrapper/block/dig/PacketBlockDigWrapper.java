package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketBlockDigWrapper extends PacketWrapper implements PacketBlockDig {
	
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
	
	@Override
	public void setPos(BlockPosition pos) {
		setField(this.pos, pos.getHandle());
	}

	@Override
	public Direction getDirection() {
		return Direction.valueOf(getFieldValue(direction, Enum.class).name());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setDirection(Direction direction) {
		setField(this.direction, Enum.valueOf((Class<? extends Enum>) this.direction.getType(), direction.name()));
	}

	@SneakyThrows
	@Override
	public Action getAction() {
		return Action.valueOf(getFieldValue(action, Enum.class).name());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
	}
}
