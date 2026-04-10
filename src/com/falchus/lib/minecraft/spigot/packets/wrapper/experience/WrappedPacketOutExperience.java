package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutExperience extends PacketExperienceWrapper {

    public WrappedPacketOutExperience(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutExperience",
                        networkProtocolGame + "PacketPlayOutExperience"
                )
        );
    }
}
