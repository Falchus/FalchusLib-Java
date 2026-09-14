package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInSpectate extends PacketSpectateWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInSpectate",
		networkProtocolGame + "PacketPlayInSpectate"
	);

	private WrappedPacketInSpectate(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketInSpectate(@NonNull UUID targetUuid) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				UUID.class,
				targetUuid
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInSpectate",
			networkProtocolGame + "PacketPlayInSpectate"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
