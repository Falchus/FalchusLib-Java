package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedScoreboardObjective extends SpigotWrapper implements ScoreboardObjective { // TODO
	
	Field displayName;

	private WrappedScoreboardObjective(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "ScoreboardObjective",
			worldScores + "ScoreboardObjective"
		));
		
		displayName = getFirstField(
			"displayName",
			"e"
		);
	}
	
	@Override
	public Object getDisplayName() {
		return getFieldValue(displayName);
	}
	
	@Override
	public void setDisplayName(String displayName) {
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			setField(this.displayName, displayName);
		} else {
			setField(this.displayName, new WrappedComponent(displayName).getHandle());
		}
	}
}
