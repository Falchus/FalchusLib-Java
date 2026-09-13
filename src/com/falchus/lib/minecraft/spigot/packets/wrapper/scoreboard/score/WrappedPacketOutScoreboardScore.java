package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardObjective;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardScore;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutScoreboardScore extends PacketScoreboardScoreWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutScoreboardScore",
		networkProtocolGame + "PacketPlayOutScoreboardScore"
	);

	private WrappedPacketOutScoreboardScore(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	public WrappedPacketOutScoreboardScore(@NonNull ScoreboardScore score) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			switch (ServerUtils.getVersion()) {
				case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> new Map[] {
					Map.of(
						score.getHandle().getClass(),
						score.getHandle()
					)
				};
				
				default -> new Map[] {
					// TODO: support
				};
			}
		).build());
	}
	
	@SuppressWarnings("unchecked")
	public WrappedPacketOutScoreboardScore(@NonNull String entry, @NonNull ScoreboardObjective objective) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			switch (ServerUtils.getVersion()) {
				case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> new Map[] {
					Map.of(
						String.class,
						entry
					),
					Map.of(
						objective.getHandle().getClass(),
						objective.getHandle()
					)
				};
				
				default -> new Map[] {
					// TODO: support
				};
			}
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutScoreboardScore",
			networkProtocolGame + "PacketPlayOutScoreboardScore"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
