package com.falchus.lib.minecraft.spigot.packets.wrapper.namedsoundeffect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketNamedSoundEffectWrapper extends PacketWrapper {

    Field x;
    Field y;
    Field z;
    Field volume;

    PacketNamedSoundEffectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        x = getFirstField(
                "x",
                "b"
        );
        y = getFirstField(
                "y",
                "c"
        );
        z = getFirstField(
                "z",
                "d"
        );
        volume = getFirstField(
                "volume",
                "e"
        );
    }

    public int getX() {
        return getFieldValue(x);
    }

    public void setX(int x) {
        setField(this.x, x);
    }

    public int getY() {
        return getFieldValue(y);
    }

    public void setY(int y) {
        setField(this.y, y);
    }

    public int getZ() {
        return getFieldValue(z);
    }

    public void setZ(int z) {
        setField(this.z, z);
    }

    public float getVolume() {
        return getFieldValue(volume);
    }

    public void setVolume(float volume) {
        setField(this.volume, volume);
    }
}
