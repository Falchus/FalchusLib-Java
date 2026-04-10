package com.falchus.lib.minecraft.spigot.packets.wrapper.collect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketCollectWrapper extends PacketWrapper {

    Field itemId;
    Field playerId;

    PacketCollectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        itemId = getFirstField(
                "itemId",
                "a"
        );
        playerId = getFirstField(
                "playerId",
                "b"
        );
    }

    public int getItemId() {
        return getFieldValue(itemId);
    }

    public void setItemId(int itemId) {
        setField(this.itemId, itemId);
    }

    public int getPlayerId() {
        return getFieldValue(playerId);
    }

    public void setPlayerId(int playerId) {
        setField(this.playerId, playerId);
    }
}
