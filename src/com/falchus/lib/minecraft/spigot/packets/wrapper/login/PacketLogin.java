package com.falchus.lib.minecraft.spigot.packets.wrapper.login;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketLogin extends IPacketWrapper {

	int getPlayerId();
	void setPlayerId(int playerId);
	
	boolean getHardcore();
	void setHardcore(boolean hardcore);
	
	int getMaxPlayers();
	void setMaxPlayers(int maxPlayers);
	
	boolean isReducedDebugInfo();
	void setReducedDebugInfo(boolean reducedDebugInfo);
}
