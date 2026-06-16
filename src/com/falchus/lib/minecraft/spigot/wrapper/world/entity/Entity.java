package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.syncher.DataWatcher;
import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;

import lombok.NonNull;

public interface Entity extends ISpigotWrapper { // TODO

	int getId();
	void setId(int id);
	
	float getYaw();
	void setYaw(float yaw);
	
	float getPitch();
	void setPitch(float pitch);
	
	AxisAlignedBB getBoundingBox();
	void setBoundingBox(AxisAlignedBB boundingBox);
	
	DataWatcher getDataWatcher();
	
	void setLocation(double x, double y, double z, float yaw, float pitch);
	
	boolean isInvisible();
	void setInvisible(boolean invisible);
	
	/**
	 * <1.13: {@link String}
	 * 1.13+: IChatBaseComponent
	 */
	Object getCustomName();
	void setCustomName(@NonNull String name);
	
	boolean isCustomNameVisible();
	void setCustomNameVisible(boolean visible);
	
	org.bukkit.entity.Entity getBukkitEntity();
}
