package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.position;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketSpawnPositionWrapper extends PacketWrapper {

    PacketSpawnPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
