package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

// TODO: add PacketPlayOutWorldParticles wrapper
@FieldDefaults(makeFinal = true)
abstract class PacketWorldEventWrapper extends PacketWrapper {

    Field type;
    Field pos;
    Field data;
    Field globalEvent;

    PacketWorldEventWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        type = getFirstField(
                "type",
                "a"
        );
        pos = getFirstField(
                "pos",
                "b"
        );
        data = getFirstField(
                "data",
                "c"
        );
        globalEvent = getFirstField(
                "globalEvent",
                "d"
        );
    }

    public int getType() {
        return getFieldValue(type);
    }

    public void setType(int type) {
        setField(this.type, type);
    }

    /**
     * @return BlockPosition
     */
    public Object getPos() {
        return getFieldValue(type);
    }

    /**
     * @param pos: BlockPosition
     */
    public void setPos(Object pos) {
        setField(this.pos, pos);
    }

    public int getData() {
        return getFieldValue(data);
    }

    public void setData(int data) {
        setField(this.data, data);
    }

    public boolean isGlobalEvent() {
        return getFieldValue(globalEvent);
    }

    public void setGlobalEvent(boolean globalEvent) {
        setField(this.globalEvent, globalEvent);
    }
}
