package com.falchus.lib.minecraft.spigot.packets.wrapper.login;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketLoginWrapper extends PacketWrapper implements PacketLogin {
=======
class PacketLoginWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field playerId;
	Field hardcore;
	Field maxPlayers;
	Field reducedDebugInfo;

	PacketLoginWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		playerId = getFirstField(
			"playerId",
			"a"
		);
		hardcore = getFirstField(
			"hardcore",
			"b"
		);
		maxPlayers = getFirstField(
			"maxPlayers",
			"f"
		);
		reducedDebugInfo = getFirstField(
			"reducedDebugInfo",
			"h"
		);
	}

	@Override
	public int getPlayerId() {
		return getFieldValue(playerId);
	}
	
	@Override
	public void setPlayerId(int playerId) {
		setField(this.playerId, playerId);
	}

	@Override
	public boolean getHardcore() {
		return getFieldValue(hardcore);
	}
	
	@Override
	public void setHardcore(boolean hardcore) {
		setField(this.hardcore, hardcore);
	}

	@Override
	public int getMaxPlayers() {
		return getFieldValue(maxPlayers);
	}
	
	@Override
	public void setMaxPlayers(int maxPlayers) {
		setField(this.maxPlayers, maxPlayers);
	}

	@Override
	public boolean isReducedDebugInfo() {
		return getFieldValue(reducedDebugInfo);
	}
	
	@Override
	public void setReducedDebugInfo(boolean reducedDebugInfo) {
		setField(this.reducedDebugInfo, reducedDebugInfo);
	}
}
