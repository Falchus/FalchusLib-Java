package com.falchus.lib.minecraft.spigot.packets.wrapper.armanimation;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketArmAnimationWrapper extends PacketWrapper {

    PacketArmAnimationWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
