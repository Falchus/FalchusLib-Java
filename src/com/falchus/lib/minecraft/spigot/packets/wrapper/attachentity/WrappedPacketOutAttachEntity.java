package com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutAttachEntity extends PacketAttachEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutAttachEntity",
		networkProtocolGame + "PacketPlayOutAttachEntity"
	);

	private WrappedPacketOutAttachEntity(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	public WrappedPacketOutAttachEntity(@NonNull Entity attachedEntity, @NonNull Entity holdingEntity) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			switch (ServerUtils.getVersion()) {
				case v1_8_8 -> new Map[] {
					Map.of(
						int.class,
						0
					),
					Map.of(
						VersionProvider.get().getEntity(),
						attachedEntity.getHandle()
					),
					Map.of(
						VersionProvider.get().getEntity(),
						holdingEntity.getHandle()
					)
				};
				
				default -> new Map[] {
					Map.of(
						VersionProvider.get().getEntity(),
						attachedEntity.getHandle()
					),
					Map.of(
						VersionProvider.get().getEntity(),
						holdingEntity.getHandle()
					)
				};
			}
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutAttachEntity",
			networkProtocolGame + "PacketPlayOutAttachEntity"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
