package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketScoreboardDisplayObjectiveWrapper extends PacketWrapper {
	
	Field objectiveName;

	PacketScoreboardDisplayObjectiveWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		objectiveName = getFirstField(
			"objectiveName",
			"b"
		);
	}

	public String getObjectiveName() {
		return getFieldValue(objectiveName);
	}
	
	public void setObjectiveName(String objectiveName) {
		setField(this.objectiveName, objectiveName);
	}
}
