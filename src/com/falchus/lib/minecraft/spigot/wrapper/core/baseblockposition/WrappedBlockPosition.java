package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

import java.util.Set;

import lombok.NonNull;

public class WrappedBlockPosition extends BaseBlockPositionWrapper {

	WrappedBlockPosition(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "BlockPosition",
			core + "BlockPosition"
		));
	}
}
