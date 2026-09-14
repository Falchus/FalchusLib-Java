package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardTeam;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboardTeam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

public class WrappedPacketOutScoreboardTeam extends PacketScoreboardTeamWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutScoreboardTeam",
		networkProtocolGame + "PacketPlayOutScoreboardTeam"
	);
	
	@Getter
	@AllArgsConstructor
	public enum Mode {
		CREATE(0),
		REMOVE(1),
		UPDATE(2),
		ADD_PLAYER(3),
		REMOVE_PLAYER(4);
		
		private final int id;
	}

	private WrappedPacketOutScoreboardTeam(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutScoreboardTeam(@NonNull ScoreboardTeam team, @NonNull Mode mode, String playerName) {
		this(VersionProvider.get().createPacketOutScoreboardTeam(names, team, mode.getId(), playerName));
	}
	
	public WrappedPacketOutScoreboardTeam(@NonNull ScoreboardTeam team, @NonNull Mode mode) {
		this(team, mode, null);
	}
	
	public WrappedPacketOutScoreboardTeam(@NonNull Mode mode, String playerName) {
		this(new WrappedScoreboardTeam(playerName), mode, playerName);
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutScoreboardTeam",
			networkProtocolGame + "PacketPlayOutScoreboardTeam"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
