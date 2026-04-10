package com.falchus.lib.minecraft.spigot.packets.wrapper.block.update;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketBlockUpdateWrapper extends PacketWrapper {

    Field pos;
    Field blockState;

    PacketBlockUpdateWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        pos = getFirstField(
                "pos",
                "a"
        );
        blockState = getFirstField(
                "blockState",
                "b"
        );
    }

    /**
     * @return BlockPosition
     */
    public Object getPos() {
        return getFieldValue(pos);
    }

    /**
     * @param pos: BlockPosition
     */
    public void setPos(Object pos) {
        setField(this.pos, pos);
    }

    /**
     * @return BlockState
     */
    public Object getBlockState() {
        return getFieldValue(blockState);
    }

    /**
     * @param block: BlockState
     */
    public void setBlockState(Object blockState) {
        setField(this.blockState, blockState);
    }
}
