package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketAnimationWrapper extends PacketWrapper implements PacketAnimation {
=======
class PacketAnimationWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field id;
	Field action;

	PacketAnimationWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getFirstField(
			"id",
			"a"
		);
		action = getFirstField(
			"action",
			"b"
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
	public int getAction() {
		return getFieldValue(action);
	}
	
	@Override
	public void setAction(int action) {
		setField(this.action, action);
	}
}
