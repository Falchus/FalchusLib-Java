package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketTabCompleteWrapper extends PacketWrapper {

    PacketTabCompleteWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
