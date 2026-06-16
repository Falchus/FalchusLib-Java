package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedScoreboardTeam extends SpigotWrapper implements ScoreboardTeam { // TODO
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "ScoreboardTeam",
		worldScores + "ScoreboardTeam"
	);
	
	Field name;
	Field players;
	Field displayName;
	Field prefix;
	Field suffix;

	private WrappedScoreboardTeam(@NonNull Object handle) {
		super(handle, names);
		
		name = getFirstField(
			"name",
			"b"
		);
		players = getFirstField(
			"players",
			"c"
		);
		displayName = getFirstField(
			"displayName",
			"d"
		);
		prefix = getFirstField(
			"playerPrefix",
			"e"
		);
		suffix = getFirstField(
			"playerSuffix",
			"f"
		);
	}
	
	public WrappedScoreboardTeam(@NonNull Scoreboard scoreboard, @NonNull String name) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				scoreboard.getHandle().getClass(),
				scoreboard.getHandle()
			),
			Map.of(
				String.class,
				name
			)
		).build());
	}
	
	public WrappedScoreboardTeam(@NonNull String name) {
		this(new WrappedScoreboard(), name);
	}
	
	@Override
	public String getName() {
		return getFieldValue(name);
	}
	
	@Override
	public Set<String> getPlayers() {
		return getFieldValue(players);
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
	
	@Override
	public Object getPrefix() {
		return getFieldValue(prefix);
	}
	
	@Override
	public void setPrefix(String prefix) {
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			setField(this.prefix, prefix);
		} else {
			setField(this.prefix, new WrappedComponent(prefix).getHandle());
		}
	}
	
	@Override
	public Object getSuffix() {
		return getFieldValue(suffix);
	}
	
	@Override
	public void setSuffix(String suffix) {
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			setField(this.suffix, suffix);
		} else {
			setField(this.suffix, new WrappedComponent(suffix).getHandle());
		}
	}
}
