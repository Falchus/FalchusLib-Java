package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import com.falchus.lib.minecraft.spigot.packets.wrapper.entity.PacketEntity;

public interface PacketUseEntity extends PacketEntity {

	public enum Action {
		INTERACT,
		ATTACK,
		INTERACT_AT
	}
	Action getAction();
	void setAction(Action action);
}
