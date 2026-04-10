package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketScoreboardObjectiveWrapper extends PacketWrapper {

    Field objectiveName;
    Field renderType;
    Field method;

    PacketScoreboardObjectiveWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        objectiveName = getFirstField(
                "objectiveName",
                "a"
        );
        renderType = getFirstField(
                "renderType",
                "c"
        );
        method = getFirstField(
                "method",
                "d"
        );
    }

    public String getObjectiveName() {
        return getFieldValue(objectiveName);
    }

    public void setObjectiveName(String objectiveName) {
        setField(this.objectiveName, objectiveName);
    }

    /**
     * @return IScoreboardCriteria$EnumScoreboardHealthDisplay
     */
    public Object getRenderType() {
        return getFieldValue(renderType);
    }

    /**
     * @param renderType: IScoreboardCriteria$EnumScoreboardHealthDisplay
     */
    public void setRenderType(Object renderType) {
        setField(this.renderType, renderType);
    }

    public int getMethod() {
        return getFieldValue(method);
    }

    public void setMethod(int method) {
        setField(this.method, method);
    }
}
