package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketScoreboardDisplayObjectiveWrapper extends PacketWrapper implements PacketScoreboardDisplayObjective {
	
	Field objectiveName;

	PacketScoreboardDisplayObjectiveWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		objectiveName = getFirstField(
			"objectiveName",
			"b"
		);
	}

	@Override
	public String getObjectiveName() {
		return getFieldValue(objectiveName);
	}
	
	@Override
	public void setObjectiveName(String objectiveName) {
		setField(this.objectiveName, objectiveName);
	}
}
