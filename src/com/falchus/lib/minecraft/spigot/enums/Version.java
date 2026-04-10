package com.falchus.lib.minecraft.spigot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum Version {
    v1_7(1, 7),
    v1_8(1, 8),
    v1_9(1, 9),
    v1_10(1, 10),
    v1_11(1, 11),
    v1_12(1, 12),
    v1_13(1, 13),
    v1_14(1, 14),
    v1_15(1, 15),
    v1_16(1, 16),
    v1_17(1, 17),
    v1_18(1, 18),
    v1_19(1, 19),
    v1_20(1, 20),
    v1_21(1, 21),
    v26_1(26, 1);

    private final int major;
    private final int minor;

    @Contract(pure = true)
    public boolean isAfter(@NotNull Version version) {
        return major > version.major || (major == version.major && minor > version.minor);
    }

    @Contract(pure = true)
    public boolean isBefore(@NotNull Version version) {
        return major < version.major || (major == version.major && minor < version.minor);
    }
}