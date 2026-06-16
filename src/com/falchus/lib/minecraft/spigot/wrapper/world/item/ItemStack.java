package com.falchus.lib.minecraft.spigot.wrapper.world.item;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface ItemStack extends ISpigotWrapper { // TODO

	int getCount();
	void setCount(int count);
	
	int getPopTime();
	void setPopTime(int popTime);
}
