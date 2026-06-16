package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface BaseBlockPosition extends ISpigotWrapper {

	int getX();
	void setX(int x);
	
	int getY();
	void setY(int y);
	
	int getZ();
	void setZ(int z);
}
