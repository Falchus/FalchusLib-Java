package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import java.util.Collection;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketScoreboardTeam extends IPacketWrapper {

	String getName();
	void setName(String name);
	
	Collection<String> getPlayers();
	void setPlayers(Collection<String> players);
	
	int getMethod();
	void setMethod(int method);
}
