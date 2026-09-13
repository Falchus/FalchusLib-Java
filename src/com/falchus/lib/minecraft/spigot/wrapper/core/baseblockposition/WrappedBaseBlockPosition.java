package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

<<<<<<< HEAD
import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedBaseBlockPosition extends BaseBlockPositionWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "BaseBlockPosition",
		core + "BaseBlockPosition"
	);

	private WrappedBaseBlockPosition(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedBaseBlockPosition(int x, int y, int z) {
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
=======
import java.util.Set;

import lombok.NonNull;

public class WrappedBaseBlockPosition extends BaseBlockPositionWrapper {

	WrappedBaseBlockPosition(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "BaseBlockPosition",
			core + "BaseBlockPosition"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
