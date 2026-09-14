package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

// TODO: add PacketPlayOutWorldParticles wrapper
@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketWorldEventWrapper extends PacketWrapper implements PacketWorldEvent {
=======
class PacketWorldEventWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field type;
	Field pos;
	Field data;
	Field globalEvent;

	PacketWorldEventWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		type = getFirstField(
			"type",
			"a"
		);
		pos = getFirstField(
			"pos",
			"b"
		);
		data = getFirstField(
			"data",
			"c"
		);
		globalEvent = getFirstField(
			"globalEvent",
			"d"
		);
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
	public BlockPosition getPos() {
		return SpigotWrapper.wrap(getFieldValue(type));
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
	public int getData() {
		return getFieldValue(data);
	}
	
	@Override
	public void setData(int data) {
		setField(this.data, data);
	}

	@Override
	public boolean isGlobalEvent() {
		return getFieldValue(globalEvent);
	}
	
	@Override
	public void setGlobalEvent(boolean globalEvent) {
		setField(this.globalEvent, globalEvent);
	}
}
