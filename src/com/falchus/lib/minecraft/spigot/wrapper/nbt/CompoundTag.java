package com.falchus.lib.minecraft.spigot.wrapper.nbt;

import java.util.Map;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface CompoundTag extends ISpigotWrapper {

	/**
	 * @return Map<String, NBTBase>
	 */
	Map<String, Object> getMap();
}
