package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketUpdateTimeWrapper extends PacketWrapper {

    Field gameTime;
    Field dayTime;

    PacketUpdateTimeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        gameTime = getFirstField(
                "gameTime",
                "a"
        );
        dayTime = getFirstField(
                "dayTime",
                "b"
        );
    }

    public long getGameTime() {
        return getFieldValue(gameTime);
    }

    public void setGameTime(long gameTime) {
        setField(this.gameTime, gameTime);
    }

    public long getDayTime() {
        return getFieldValue(dayTime);
    }

    public void setDayTime(long dayTime) {
        setField(this.dayTime, dayTime);
    }
}
