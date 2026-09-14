package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketScoreboardScoreWrapper extends PacketWrapper implements PacketScoreboardScore {
=======
class PacketScoreboardScoreWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
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

	@Override
	public String getOwner() {
		return getFieldValue(owner);
	}
	
	@Override
	public void setOwner(String owner) {
		setField(this.owner, owner);
	}

	@Override
	public String getObjectiveName() {
		return getFieldValue(objectiveName);
	}
	
	@Override
	public void setObjectiveName(String objectiveName) {
		setField(this.objectiveName, objectiveName);
	}

	@Override
	public int getScore() {
		return getFieldValue(score);
	}
	
	@Override
	public void setScore(int score) {
		setField(this.score, score);
	}
}
