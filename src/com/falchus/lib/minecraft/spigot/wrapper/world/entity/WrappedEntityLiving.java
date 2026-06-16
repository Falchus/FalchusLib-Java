package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import java.util.Set;

import org.bukkit.entity.LivingEntity;

import com.falchus.lib.minecraft.spigot.utils.EntityUtils;

import lombok.NonNull;

public class WrappedEntityLiving extends EntityLivingWrapper {

	private WrappedEntityLiving(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "EntityLiving",
			worldEntity + "EntityLiving"
		));
	}
	
	public WrappedEntityLiving(@NonNull LivingEntity entity) {
		this(EntityUtils.getEntityLiving(entity));
	}
}
