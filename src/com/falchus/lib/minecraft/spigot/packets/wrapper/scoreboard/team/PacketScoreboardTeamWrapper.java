package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketScoreboardTeamWrapper extends PacketWrapper {
	
	Field name;
	Field players;
	Field method;

	PacketScoreboardTeamWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		name = getFirstField(
			"name",
			"a"
		);
		players = getFirstField(
			"players",
			"g"
		);
		method = getFirstField(
			"method",
			"h"
		);
	}

	public String getName() {
		return getFieldValue(name);
	}
	
	public void setName(String name) {
		setField(this.name, name);
	}

	public Collection<String> getPlayers() {
		return getFieldValue(players);
	}
	
	public void setPlayers(Collection<String> players) {
		setField(this.players, players);
	}

	public int getMethod() {
		return getFieldValue(method);
	}
	
	public void setMethod(int method) {
		setField(this.method, method);
	}
}
