package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketTitleWrapper extends PacketWrapper {

    PacketTitleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
