package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.world.entity.player.PlayerAbilities;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInAbilities extends PacketAbilitiesWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInAbilities",
		networkProtocolGame + "PacketPlayInAbilities"
	);

	private WrappedPacketInAbilities(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketInAbilities(@NonNull PlayerAbilities abilities) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				abilities.getHandle().getClass(),
				abilities.getHandle()
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInAbilities",
			networkProtocolGame + "PacketPlayInAbilities"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
