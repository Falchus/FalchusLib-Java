package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketScoreboardScoreWrapper extends PacketWrapper {

    Field owner;
    Field objectiveName;
    Field score;

    PacketScoreboardScoreWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        owner = getFirstField(
                "owner",
                "a"
        );
        objectiveName = getFirstField(
                "objectiveName",
                "b"
        );
        score = getFirstField(
                "score",
                "c"
        );
    }

    public String getOwner() {
        return getFieldValue(owner);
    }

    public void setOwner(String owner) {
        setField(this.owner, owner);
    }

    public String getObjectiveName() {
        return getFieldValue(objectiveName);
    }

    public void setObjectiveName(String objectiveName) {
        setField(this.objectiveName, objectiveName);
    }

    public int getScore() {
        return getFieldValue(score);
    }

    public void setScore(int score) {
        setField(this.score, score);
    }
}
