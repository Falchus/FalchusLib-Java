package com.falchus.lib.minecraft.spigot.wrapper.nbt;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedCompoundTag extends SpigotWrapper implements CompoundTag {
	
	Field map;

	private WrappedCompoundTag(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "NBTTagCompound",
			nbt + "NBTTagCompound"
		));
		
		map = getFirstField(
			"map",
			"tags"
		);
	}
	
	@Override
	public Map<String, Object> getMap() {
		return getFieldValue(map);
	}
}
