package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketSetCreativeSlotWrapper extends PacketWrapper {

    Field itemStack;

    PacketSetCreativeSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        itemStack = getFirstField(
                "itemStack",
                "b"
        );
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
