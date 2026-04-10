package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInEnchantItem extends PacketEnchantItemWrapper {

    public WrappedPacketInEnchantItem(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInEnchantItem",
                        networkProtocolGame + "PacketPlayInEnchantItem"
                )
        );
    }
}
