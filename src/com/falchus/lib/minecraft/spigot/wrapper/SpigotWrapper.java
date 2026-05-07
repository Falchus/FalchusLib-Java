package com.falchus.lib.minecraft.spigot.wrapper;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.IVersionAdapter;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.core.blockposition.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.*;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;
import com.falchus.lib.utils.wrapper.impl.RegistryFirstClassWrapper;

import lombok.NonNull;

public class SpigotWrapper extends RegistryFirstClassWrapper<Object> {

	protected static final IVersionAdapter version = VersionProvider.get();
	
	private static final String networkProtocol = version.getPackageNm() + "network.protocol.";
	protected static final String networkProtocolCommon = networkProtocol + "common.";
	protected static final String networkProtocolGame = networkProtocol + "game.";
	
	private static final String world = version.getPackageNm() + "world.";
	protected static final String worldPhys = world + "phys.";
	
	protected static final String core = version.getPackageNm() + "core.";
	
	public SpigotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	protected Class<? extends FirstClassWrapper<?>>[] getWrappers() {
		return new Class[] {
			WrappedBaseBlockPosition.class,
			WrappedBlockPosition.class,

			WrappedAxisAlignedBB.class
		};
	}
}
