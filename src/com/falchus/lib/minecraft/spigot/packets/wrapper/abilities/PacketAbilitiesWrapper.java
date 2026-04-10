package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketAbilitiesWrapper extends PacketWrapper {

    Field flying;

    PacketAbilitiesWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        flying = getFirstField(
                "isFlying",
                "b"
        );
    }

    public boolean isFlying() {
        return getFieldValue(flying);
    }

    public void setFlying(boolean isFlying) {
        setField(this.flying, isFlying);
    }
}
