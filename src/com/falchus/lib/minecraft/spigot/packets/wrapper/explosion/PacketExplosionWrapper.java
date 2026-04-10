package com.falchus.lib.minecraft.spigot.packets.wrapper.explosion;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketExplosionWrapper extends PacketWrapper {

    Field radius;

    PacketExplosionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        radius = getFirstField(
                "radius",
                "power",
                "d"
        );
    }

    public float getRadius() {
        return getFieldValue(radius);
    }

    public void setRadius(float radius) {
        setField(this.radius, radius);
    }
}
