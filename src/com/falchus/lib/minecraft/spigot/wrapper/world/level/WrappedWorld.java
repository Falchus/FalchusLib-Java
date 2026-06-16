package com.falchus.lib.minecraft.spigot.wrapper.world.level;

import java.util.Set;

import org.bukkit.World;

import com.falchus.lib.minecraft.spigot.utils.WorldUtils;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedWorld extends SpigotWrapper { // TODO

	private WrappedWorld(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "World",
			worldLevel + "World"
		));
	}
	
	public WrappedWorld(@NonNull World world) {
		this(WorldUtils.getWorld(world));
	}
}
