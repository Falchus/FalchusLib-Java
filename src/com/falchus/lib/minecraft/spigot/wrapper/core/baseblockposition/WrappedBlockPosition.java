package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedBlockPosition extends BaseBlockPositionWrapper implements BlockPosition {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "BlockPosition",
		core + "BlockPosition"
	);

	private WrappedBlockPosition(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedBlockPosition(int x, int y, int z) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				x
			),
			Map.of(
				int.class,
				y
			),
			Map.of(
				int.class,
				z
			)
		).build());
	}
}
