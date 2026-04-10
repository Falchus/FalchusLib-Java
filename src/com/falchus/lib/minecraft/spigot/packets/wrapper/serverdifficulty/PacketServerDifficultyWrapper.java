package com.falchus.lib.minecraft.spigot.packets.wrapper.serverdifficulty;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketServerDifficultyWrapper extends PacketWrapper {

    Field difficulty;
    Field locked;

    PacketServerDifficultyWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        difficulty = getFirstField(
                "difficulty",
                "a"
        );
        locked = getFirstField(
                "locked",
                "b"
        );
    }

    /**
     * @return EnumDifficulty
     */
    public Object getDifficulty() {
        return getFieldValue(difficulty);
    }

    /**
     * @param difficulty: EnumDifficulty
     */
    public void setDifficulty(Object difficulty) {
        setField(this.difficulty, difficulty);
    }

    public boolean isLocked() {
        return getFieldValue(locked);
    }

    public void setLocked(boolean locked) {
        setField(this.locked, locked);
    }
}
