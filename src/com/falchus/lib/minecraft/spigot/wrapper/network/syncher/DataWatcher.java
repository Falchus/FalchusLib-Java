package com.falchus.lib.minecraft.spigot.wrapper.network.syncher;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;

public interface DataWatcher extends ISpigotWrapper {

	Entity getEntity();
	void setEntity(Entity entity);
	
	boolean isDirty();
	void setDirty(boolean dirty);
}
