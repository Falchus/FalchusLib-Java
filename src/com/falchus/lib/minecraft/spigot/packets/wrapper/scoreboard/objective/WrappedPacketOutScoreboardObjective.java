package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardObjective;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutScoreboardObjective extends PacketScoreboardObjectiveWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutScoreboardObjective",
		networkProtocolGame + "PacketPlayOutScoreboardObjective"
	);

	private WrappedPacketOutScoreboardObjective(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutScoreboardObjective(@NonNull ScoreboardObjective objective, int mode) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				objective.getHandle().getClass(),
				objective.getHandle()
			),
			Map.of(
				int.class,
				mode
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutScoreboardObjective",
			networkProtocolGame + "PacketPlayOutScoreboardObjective"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
