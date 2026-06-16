package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardObjective;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

public class WrappedPacketOutScoreboardDisplayObjective extends PacketScoreboardDisplayObjectiveWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutScoreboardDisplayObjective",
		networkProtocolGame + "PacketPlayOutScoreboardDisplayObjective"
	);

	private WrappedPacketOutScoreboardDisplayObjective(@NonNull Object handle) {
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	public WrappedPacketOutScoreboardDisplayObjective(@NonNull DisplaySlot slot, @NonNull ScoreboardObjective objective) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			switch (ServerUtils.getVersion()) {
				case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1, v1_18, v1_18_1, v1_18_2, v1_19, v1_19_1, v1_19_2, v1_19_3, v1_19_4, v1_20_1 -> new Map[] {
					Map.of(
						int.class,
						slot.getSlot()
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
	}
	
	@Getter
	@AllArgsConstructor
	public enum DisplaySlot {
		LIST(0),
		SIDEBAR(1),
		BELOW_NAME(2),
		
	    TEAM_BLACK(3),
	    TEAM_DARK_BLUE(4),
	    TEAM_DARK_GREEN(5),
	    TEAM_DARK_AQUA(6),
	    TEAM_DARK_RED(7),
	    TEAM_DARK_PURPLE(8),
	    TEAM_GOLD(9),
	    TEAM_GRAY(10),
	    TEAM_DARK_GRAY(11),
	    TEAM_BLUE(12),
	    TEAM_GREEN(13),
	    TEAM_AQUA(14),
	    TEAM_RED(15),
	    TEAM_LIGHT_PURPLE(16),
	    TEAM_YELLOW(17),
	    TEAM_WHITE(18);
		
		private final int slot;
	}
}
