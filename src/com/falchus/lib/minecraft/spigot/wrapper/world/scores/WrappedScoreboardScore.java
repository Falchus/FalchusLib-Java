package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedScoreboardScore extends SpigotWrapper implements ScoreboardScore { // TODO
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "ScoreboardScore",
		worldScores + "ScoreboardScore"
	);
	
	Field score;

	private WrappedScoreboardScore(@NonNull Object handle) {
		super(handle, names);
		
		score = getFirstField(
			"score",
			"value"
		);
	}
	
	// TODO: 1.21 support
	public WrappedScoreboardScore(@NonNull Scoreboard scoreboard, @NonNull ScoreboardObjective objective, @NonNull String playerName) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				scoreboard.getHandle().getClass(),
				scoreboard.getHandle()
			),
			Map.of(
				objective.getHandle().getClass(),
				objective.getHandle()
			),
			Map.of(
				String.class,
				playerName
			)
		).build());
	}
	
	@Override
	public int getScore() {
		return getFieldValue(score);
	}
	
	@Override
	public void setScore(int score) {
		setField(this.score, score);
	}
}
