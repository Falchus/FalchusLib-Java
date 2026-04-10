package com.falchus.lib.minecraft.spigot.packets.wrapper.settings;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;

import java.util.Set;

abstract class PacketSettingsWrapper extends PacketWrapper {

    PacketSettingsWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }
}
