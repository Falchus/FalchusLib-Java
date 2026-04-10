package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutTitleTitle extends PacketTitleWrapper {

    Field text;

    WrappedPacketOutTitleTitle(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutTitle",
                        networkProtocolGame + "ClientboundSetTitleTextPacket"
                )
        );

        text = getFirstField(
                "text",
                "b"
        );
    }

    /**
     * @return IChatBaseComponent
     */
    public Object getText() {
        return getFieldValue(text);
    }

    /**
     * @param text: IChatBaseComponent
     */
    public void setText(Object text) {
        setField(this.text, text);
    }
}
