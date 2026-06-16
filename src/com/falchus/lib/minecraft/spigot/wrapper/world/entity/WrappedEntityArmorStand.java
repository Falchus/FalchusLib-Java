package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import java.util.Map;
import java.util.Set;

import org.bukkit.entity.ArmorStand;

import com.falchus.lib.minecraft.spigot.utils.EntityUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.World;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedEntityArmorStand extends EntityLivingWrapper implements EntityArmorStand { // TODO
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "EntityArmorStand",
		worldEntityDecoration + "EntityArmorStand"
	);

	private WrappedEntityArmorStand(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedEntityArmorStand(@NonNull ArmorStand entity) {
		this(EntityUtils.getEntityLiving(entity));
	}
	
	public WrappedEntityArmorStand(@NonNull World world, double x, double y, double z) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getWorld(),
				world.getHandle()
			),
			Map.of(
				double.class,
				x
			),
			Map.of(
				double.class,
				y
			),
			Map.of(
				double.class,
				z
			)
		).build());
	}
	
	@Override
	public ArmorStand getBukkitEntity() {
		return (ArmorStand) super.getBukkitEntity();
	}
}
