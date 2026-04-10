package com.falchus.lib.minecraft.spigot.packets.wrapper.kickdisconnect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketKickDisconnectWrapper extends PacketWrapper {

    Field reason;

    PacketKickDisconnectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        reason = getFirstField(
                "reason",
                "a"
        );
    }

    /**
     * @return IChatBaseComponent
     */
    public Object getReason() {
        return getFieldValue(reason);
    }

    /**
     * @param reason: IChatBaseComponent
     */
    public void setReason(Object reason) {
        setField(this.reason, reason);
    }
}
