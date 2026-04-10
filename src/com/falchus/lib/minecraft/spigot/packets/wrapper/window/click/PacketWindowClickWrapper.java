package com.falchus.lib.minecraft.spigot.packets.wrapper.window.click;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketWindowClickWrapper extends PacketWrapper {

    Field containerId;
    Field slotNum;
    Field buttonNum;
    Field uid;

    PacketWindowClickWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        containerId = getFirstField(
                "containerId",
                "a"
        );
        slotNum = getFirstField(
                "slotNum",
                "b"
        );
        buttonNum = getFirstField(
                "buttonNum",
                "c"
        );
        uid = getFirstField(
                "uid",
                "d"
        );
    }

    public int getContainerId() {
        return getFieldValue(containerId);
    }

    public void setContainerId(int containerId) {
        setField(this.containerId, containerId);
    }

    public int getSlotNum() {
        return getFieldValue(slotNum);
    }

    public void setSlotNum(int slotNum) {
        setField(this.slotNum, slotNum);
    }

    public int getButtonNum() {
        return getFieldValue(buttonNum);
    }

    public void setButtonNum(int buttonNum) {
        setField(this.buttonNum, buttonNum);
    }

    public short getUID() {
        return getFieldValue(uid);
    }

    public void setUID(short uid) {
        setField(this.uid, uid);
    }
}
