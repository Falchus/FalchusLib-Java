package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketPositionWrapper extends PacketWrapper {

    PacketPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
