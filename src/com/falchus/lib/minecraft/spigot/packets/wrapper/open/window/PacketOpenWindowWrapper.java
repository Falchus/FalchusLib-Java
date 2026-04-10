package com.falchus.lib.minecraft.spigot.packets.wrapper.open.window;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketOpenWindowWrapper extends PacketWrapper {

    Field containerId;
    Field title;

    PacketOpenWindowWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        containerId = getFirstField(
                "containerId",
                "a"
        );
        title = getFirstField(
                "title",
                "c"
        );
    }

    public int getContainerId() {
        return getFieldValue(containerId);
    }

    public void setContainerId(int containerId) {
        setField(this.containerId, containerId);
    }

    /**
     * @return IChatBaseComponent
     */
    public Object getTitle() {
        return getFieldValue(title);
    }

    /**
     * @param title: IChatBaseComponent
     */
    public void setTitle(Object title) {
        setField(this.title, title);
    }
}
