package com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.NonNull;

public class WrappedPacketOutPlayerListHeaderFooter extends PacketPlayerListHeaderFooterWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutPlayerListHeaderFooter",
		networkProtocolGame + "PacketPlayOutPlayerListHeaderFooter"
	);

	private WrappedPacketOutPlayerListHeaderFooter(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutPlayerListHeaderFooter(@NonNull String header, @NonNull String footer) {
		this(VersionProvider.get().createPacketOutPlayerListHeaderFooter(names, header, footer));
	}
}
