package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import lombok.NonNull;

import java.util.Set;

abstract class PacketUpdateWindowItems extends PacketUpdateWindow {

    PacketUpdateWindowItems(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
