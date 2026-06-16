package com.falchus.lib.minecraft.spigot.wrapper.world.level.block;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedBlock extends SpigotWrapper implements Block { // TODO

	private WrappedBlock(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "Block",
			worldLevelBlock + "Block"
		));
	}
}
