package com.falchus.lib.minecraft.spigot.wrapper.world;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface AxisAlignedBB extends ISpigotWrapper {

	double getMinX();
	void setMinX(double minX);
	
	double getMinY();
	void setMinY(double minY);
	
	double getMinZ();
	void setMinZ(double minZ);
	
	double getMaxX();
	void setMaxX(double maxX);
	
	double getMaxY();
	void setMaxY(double maxY);
	
	double getMaxZ();
	void setMaxZ(double maxZ);
}
