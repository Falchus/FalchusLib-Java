package com.falchus.lib.minecraft.spigot.wrapper.network.protocol.status;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;
import com.mojang.authlib.GameProfile;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedServerPing extends SpigotWrapper implements ServerPing {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "ServerPing",
		networkProtocolStatus + "ServerPing"
	);
	
	Field description;
	Field players;

	private WrappedServerPing(@NonNull Object handle) {
		super(handle, names);
		
		description = getFirstField(
			"description",
			"a"
		);
		players = getFirstField(
			"players",
			"b"
		);
	}
	
	@Override
	public Component getDescription() {
		return wrap(getFieldValue(description));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Players getPlayers() {
		Object players = getFieldValue(this.players);
		if (ServerUtils.getVersion().isAfter(Version.v1_19_3)) {
			players = ((Optional<Object>) getFieldValue(this.players)).get();
		}
		return wrap(players);
	}
	
	public static class WrappedPlayers extends SpigotWrapper implements Players {
		
		private static final Set<String> names = Set.of(
			version.getPackageNms() + "ServerPing$ServerPingPlayerSample",
			networkProtocolStatus + "ServerPing$ServerPingPlayerSample"
		);
		
		Field maxPlayers;
		Field onlinePlayers;
		Field sample;
		
		private WrappedPlayers(@NonNull Object handle) {
			super(handle, names);
			
			maxPlayers = getFirstField(
				"maxPlayers",
				"a"
			);
			onlinePlayers = getFirstField(
				"numPlayers",
				"b"
			);
			sample = getFirstField(
				"sample",
				"c"
			);
		}
		
		@Override
		public int getMaxPlayers() {
			return getFieldValue(maxPlayers);
		}
		
		@Override
		public int getOnlinePlayers() {
			return getFieldValue(onlinePlayers);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<GameProfile> getSample() { // TODO: 1.21.9+ support
			Object value = getFieldValue(sample);
			if (value == null) return List.of();
			
			if (value instanceof List) {
				return (List<GameProfile>) value;
			}
			return Arrays.asList((GameProfile[]) value);
		}
	}
}
