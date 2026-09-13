package com.falchus.lib.minecraft.spigot.packets.wrapper.respawn;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketRespawnWrapper extends PacketWrapper implements PacketRespawn { // TODO
=======
class PacketRespawnWrapper extends PacketWrapper {
	
	Field levelType;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketRespawnWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
<<<<<<< HEAD
=======
		
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
	 * @param levelType	WorldType
	 */
	public void setLevelType(Object levelType) {
		setField(this.levelType, levelType);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
