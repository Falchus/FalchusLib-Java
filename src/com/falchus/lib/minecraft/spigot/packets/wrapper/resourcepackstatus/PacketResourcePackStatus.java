package com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketResourcePackStatus extends IPacketWrapper {

	public enum Status {
		SUCCESSFULLY_LOADED,
		DECLINED,
		FAILED_DOWNLOAD,
		ACCEPTED
	}
	Status getStatus();
	void setStatus(Status status);
}
