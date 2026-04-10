package com.falchus.lib.minecraft.spigot.packets.wrapper.statistic;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketStatisticWrapper extends PacketWrapper {

    PacketStatisticWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
