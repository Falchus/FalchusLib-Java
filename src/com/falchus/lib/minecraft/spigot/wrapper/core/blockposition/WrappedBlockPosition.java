package com.falchus.lib.minecraft.spigot.wrapper.core.blockposition;

import java.util.Set;

import lombok.NonNull;

public class WrappedBlockPosition extends BaseBlockPositionWrapper {

	WrappedBlockPosition(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, Set.of(
			version.getPackageNms() + "BlockPosition",
			core + "BlockPosition"
		));
	}
}
