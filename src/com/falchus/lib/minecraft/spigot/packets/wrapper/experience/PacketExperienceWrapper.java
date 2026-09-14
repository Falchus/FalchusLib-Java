package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketExperienceWrapper extends PacketWrapper implements PacketExperience {
=======
class PacketExperienceWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field experienceProgress;
	Field totalExperience;
	Field experienceLevel;

	PacketExperienceWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		experienceProgress = getFirstField(
			"experienceProgress",
			"a"
		);
		totalExperience = getFirstField(
			"totalExperience",
			"b"
		);
		experienceLevel = getFirstField(
			"experienceLevel",
			"c"
		);
	}

	@Override
	public float getExperienceProgress() {
		return getFieldValue(experienceProgress);
	}
	
	@Override
	public void setExperienceProgress(float experienceProgress) {
		setField(this.experienceProgress, experienceProgress);
	}

	@Override
	public int getTotalExperience() {
		return getFieldValue(totalExperience);
	}
	
	@Override
	public void setTotalExperience(int totalExperience) {
		setField(this.totalExperience, totalExperience);
	}

	@Override
	public int getExperienceLevel() {
		return getFieldValue(experienceLevel);
	}
	
	@Override
	public void setExperienceLevel(int experienceLevel) {
		setField(this.experienceLevel, experienceLevel);
	}
}
