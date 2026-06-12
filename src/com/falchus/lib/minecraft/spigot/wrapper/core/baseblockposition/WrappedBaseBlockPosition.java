package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

import java.util.Set;

import lombok.NonNull;

public class WrappedBaseBlockPosition extends BaseBlockPositionWrapper {

	WrappedBaseBlockPosition(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "BaseBlockPosition",
			core + "BaseBlockPosition"
		));
	}
}
