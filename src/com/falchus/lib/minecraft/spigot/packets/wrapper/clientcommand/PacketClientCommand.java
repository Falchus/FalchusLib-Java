package com.falchus.lib.minecraft.spigot.packets.wrapper.clientcommand;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketClientCommand extends IPacketWrapper {

	public enum Action {
		PERFORM_RESPAWN,
		REQUEST_STATS
	}
	Action getAction();
	void setAction(Action action);
}
