package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketAbilitiesWrapper extends PacketWrapper implements PacketAbilities {
=======
class PacketAbilitiesWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field flying;

	PacketAbilitiesWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		flying = getFirstField(
			"isFlying",
			"b"
		);
	}

	@Override
	public boolean isFlying() {
		return getFieldValue(flying);
	}
	
	@Override
	public void setFlying(boolean isFlying) {
		setField(this.flying, isFlying);
	}
}
