package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.ScoreboardRenderType;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria.ScoreboardCriteria;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedScoreboard extends SpigotWrapper implements Scoreboard { // TODO
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "Scoreboard",
		worldScores + "Scoreboard"
	);
	
	Method registerObjective;
	Method getPlayers;
	Method unregisterObjective;
	Method getTeam;
	Method getTeams;
	Method createTeam;
	Method addPlayerToTeam;

	private WrappedScoreboard(@NonNull Object handle) {
		super(handle, names);
		
		registerObjective = VersionProvider.get().scoreboard_registerObjective();
		getPlayers = getFirstMethod(
			List.of(),
			"getPlayers",
			"getTrackedPlayers"
		);
		unregisterObjective = VersionProvider.get().scoreboard_unregisterObjective();
		getTeam = getFirstMethod(
			List.of(
				String.class
			),
			"getTeam",
			"getPlayerTeam"
		);
		getTeams = getFirstMethod(
			List.of(),
			"getTeams",
			"getPlayerTeams"
		);
		createTeam = getFirstMethod(
			List.of(
				String.class
			),
			"createTeam",
			"addPlayerTeam"
		);
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			addPlayerToTeam = getMethod("addPlayerToTeam",
				String.class,
				String.class
			);
		} else {
			addPlayerToTeam = getMethod("addPlayerToTeam",
				String.class,
				Object.class
			);
		}
	}
	
	public WrappedScoreboard() {
		this(new ClassInstanceBuilder(
			names
		).build());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ScoreboardObjective registerObjective(@NonNull String name, @NonNull ScoreboardCriteria criteria) {
		try {
			if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
				return wrap(registerObjective.invoke(handle,
					name,
					criteria.getHandle()
				));
			}
			
			Object component = new WrappedComponent(name).getHandle();
			Object renderType = Enum.valueOf((Class<? extends Enum>) VersionProvider.get().getIScoreboardCriteria$enumScoreboardHealthDisplay(), ScoreboardRenderType.INTEGER.name());
			if (ServerUtils.getVersion().isBefore(Version.v1_20_4)) {
				return wrap(registerObjective.invoke(handle,
					name,
					criteria.getHandle(),
					component,
					renderType
				));
			}
			
			return wrap(registerObjective.invoke(handle,
				name,
				criteria.getHandle(),
				component,
				renderType,
				false,
				null
			));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getPlayers() {
		try {
			if (ServerUtils.getVersion().isBefore(Version.v1_20_4)) {
				return (Collection<String>) getPlayers.invoke(handle);
			}
			return ((Collection<?>) getPlayers.invoke(handle)).stream()
					.map(obj -> {
						try {
							return (String) ReflectionUtils.getMethod(obj, "getScoreboardName").invoke(obj);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					})
					.toList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void unregisterObjective(@NonNull ScoreboardObjective objective) {
		try {
			unregisterObjective.invoke(handle,
				objective.getHandle()
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ScoreboardTeam getTeam(@NonNull String name) {
		try {
			return wrap(getTeam.invoke(handle,
				name
			));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Collection<ScoreboardTeam> getTeams() {
		try {
			return ((Collection<?>) getTeams.invoke(handle)).stream()
					.map(SpigotWrapper::<ScoreboardTeam>wrap)
					.toList();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ScoreboardTeam createTeam(@NonNull String name) {
		try {
			return wrap(createTeam.invoke(handle,
				name
			));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean addPlayerToTeam(@NonNull String name, @NonNull ScoreboardTeam team) {
		try {
			return (boolean) addPlayerToTeam.invoke(handle,
				switch (ServerUtils.getVersion()) {
					case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> new Object[] {
						name,
						team.getName()
					};
					
					default -> new Object[] {
						name,
						team.getHandle()
					};
				}
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
