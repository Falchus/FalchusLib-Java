package com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedScoreboardCriteria extends SpigotWrapper implements ScoreboardCriteria { // TODO
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "IScoreboardCriteria",
		worldScoresCriteria + "IScoreboardCriteria"
	);

	private WrappedScoreboardCriteria(@NonNull Object handle) {
		super(handle, names);
	}
	
	public static ScoreboardCriteria dummy() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"DUMMY",
				"b"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ScoreboardCriteria trigger() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"TRIGGER",
				"c"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ScoreboardCriteria deathCount() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"DEATH_COUNT",
				"d"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ScoreboardCriteria playerKillCount() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"PLAYER_KILL_COUNT",
				"KILL_COUNT_PLAYERS",
				"e"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ScoreboardCriteria totalKillCount() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"TOTAL_KILL_COUNT",
				"KILL_COUNT_ALL",
				"f"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static ScoreboardCriteria health() {
		try {
			return new WrappedScoreboardCriteria(ReflectionUtils.getFirstField(ReflectionUtils.getFirstClass(names),
				"HEALTH",
				"g"
			).get(null));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
