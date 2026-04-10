package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketExperienceWrapper extends PacketWrapper {

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

    public float getExperienceProgress() {
        return getFieldValue(experienceProgress);
    }

    public void setExperienceProgress(float experienceProgress) {
        setField(this.experienceProgress, experienceProgress);
    }

    public int getTotalExperience() {
        return getFieldValue(totalExperience);
    }

    public void setTotalExperience(int totalExperience) {
        setField(this.totalExperience, totalExperience);
    }

    public int getExperienceLevel() {
        return getFieldValue(experienceLevel);
    }

    public void setExperienceLevel(int experienceLevel) {
        setField(this.experienceLevel, experienceLevel);
    }
}
