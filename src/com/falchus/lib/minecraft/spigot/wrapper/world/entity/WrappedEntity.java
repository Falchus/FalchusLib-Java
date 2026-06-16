package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import java.util.Set;

import org.bukkit.entity.Entity;

import com.falchus.lib.minecraft.spigot.utils.EntityUtils;

import lombok.NonNull;

public class WrappedEntity extends EntityWrapper {

	private WrappedEntity(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "Entity",
			worldEntity + "Entity"
		));
	}
	
	public WrappedEntity(@NonNull Entity entity) {
		this(EntityUtils.getEntity(entity));
	}
}
