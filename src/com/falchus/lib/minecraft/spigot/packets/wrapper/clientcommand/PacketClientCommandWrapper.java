package com.falchus.lib.minecraft.spigot.packets.wrapper.clientcommand;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketClientCommandWrapper extends PacketWrapper implements PacketClientCommand {
=======
class PacketClientCommandWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field action;

	PacketClientCommandWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		action = getFirstField(
			"action",
			"a"
		);
	}

	@Override
	public Action getAction() {
		return Action.valueOf(getFieldValue(action, Enum.class).name());
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
=======
	/**
	 * @param action	PacketPlayInClientCommand$EnumClientCommand
	 */
	public void setAction(Object action) {
		setField(this.action, action);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
