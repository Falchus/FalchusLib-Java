package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketHeldItemSlotWrapper extends PacketWrapper {

    Field slot;

    PacketHeldItemSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        slot = getFirstField(
                "slot",
                "a"
        );
    }

    public int getSlot() {
        return getFieldValue(slot);
    }

    public void setSlot(int slot) {
        setField(this.slot, slot);
    }
}
