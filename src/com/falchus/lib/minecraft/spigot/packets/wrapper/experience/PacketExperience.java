package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketExperience extends IPacketWrapper {

	float getExperienceProgress();
	void setExperienceProgress(float experienceProgress);
	
	int getTotalExperience();
	void setTotalExperience(int totalExperience);
	
	int getExperienceLevel();
	void setExperienceLevel(int experienceLevel);
}
