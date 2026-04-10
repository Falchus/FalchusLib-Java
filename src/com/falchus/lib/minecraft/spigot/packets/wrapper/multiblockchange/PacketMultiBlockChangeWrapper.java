package com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketMultiBlockChangeWrapper extends PacketWrapper {

    PacketMultiBlockChangeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
