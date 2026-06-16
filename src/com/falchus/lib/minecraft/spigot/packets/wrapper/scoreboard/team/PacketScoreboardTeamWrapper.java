package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketScoreboardTeamWrapper extends PacketWrapper implements PacketScoreboardTeam {
	
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

	@Override
	public String getName() {
		return getFieldValue(name);
	}
	
	@Override
	public void setName(String name) {
		setField(this.name, name);
	}

	@Override
	public Collection<String> getPlayers() {
		return getFieldValue(players);
	}
	
	@Override
	public void setPlayers(Collection<String> players) {
		setField(this.players, players);
	}

	@Override
	public int getMethod() {
		return getFieldValue(method);
	}
	
	@Override
	public void setMethod(int method) {
		setField(this.method, method);
	}
}
