package com.falchus.lib.minecraft.spigot.packets.wrapper.respawn;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketRespawnWrapper extends PacketWrapper {
	
	Field levelType;

	PacketRespawnWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		levelType = getFirstField(
			"levelType",
			"d"
		);
	}

	/**
	 * @return WorldType
	 */
	public Object getLevelType() {
		return getFieldValue(levelType);
	}
	
	/**
	 * @param levelType: WorldType
	 */
	public void setLevelType(Object levelType) {
		setField(this.levelType, levelType);
	}
}
