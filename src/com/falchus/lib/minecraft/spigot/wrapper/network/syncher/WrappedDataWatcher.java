package com.falchus.lib.minecraft.spigot.wrapper.network.syncher;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedDataWatcher extends SpigotWrapper implements DataWatcher {
	
	Field entity;
	Field dirty;

	private WrappedDataWatcher(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "DataWatcher",
			networkSynched + "DataWatcher"
		));
		
		entity = getFirstField(
			"entity",
			"a"
		);
		dirty = getFirstField(
			"isDirty",
			"e"
		);
	}
	
	@Override
	public Entity getEntity() {
		return wrap(getFieldValue(entity));
	}
	
	@Override
	public void setEntity(Entity entity) {
		setField(this.entity, entity.getHandle());
	}
	
	@Override
	public boolean isDirty() {
		return getFieldValue(dirty);
	}
	
	@Override
	public void setDirty(boolean dirty) {
		setField(this.dirty, dirty);
	}
}
