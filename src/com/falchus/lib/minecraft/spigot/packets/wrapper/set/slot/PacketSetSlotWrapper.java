package com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketSetSlotWrapper extends PacketWrapper {

    Field containerId;
    Field slot;
    Field itemStack;

    PacketSetSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        containerId = getFirstField(
                "containerId",
                "a"
        );
        slot = getFirstField(
                "slot",
                "b"
        );
        itemStack = getFirstField(
                "itemStack",
                "c"
        );
    }

    public int getContainerId() {
        return getFieldValue(containerId);
    }

    public void setContainerId(int containerId) {
        setField(this.containerId, containerId);
    }

    public int getSlot() {
        return getFieldValue(slot);
    }

    public void setSlot(int slot) {
        setField(this.slot, slot);
    }

    /**
     * @return ItemStack
     */
    public Object getItemStack() {
        return getFieldValue(itemStack);
    }

    /**
     * @param itemStack: ItemStack
     */
    public void setItemStack(Object itemStack) {
        setField(this.itemStack, itemStack);
    }
}
