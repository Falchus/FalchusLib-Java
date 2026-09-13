package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.ScoreboardRenderType;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketScoreboardObjectiveWrapper extends PacketWrapper implements PacketScoreboardObjective {
=======
class PacketScoreboardObjectiveWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
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

	@Override
	public String getObjectiveName() {
		return getFieldValue(objectiveName);
	}
	
	@Override
	public void setObjectiveName(String objectiveName) {
		setField(this.objectiveName, objectiveName);
	}

	@Override
	public ScoreboardRenderType getRenderType() {
		return ScoreboardRenderType.valueOf(getFieldValue(renderType, Enum.class).name());
	}
	
<<<<<<< HEAD
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setRenderType(ScoreboardRenderType renderType) {
		setField(this.renderType, Enum.valueOf((Class<? extends Enum>) this.renderType.getType(), renderType.name()));
=======
	/**
	 * @param renderType	IScoreboardCriteria$EnumScoreboardHealthDisplay
	 */
	public void setRenderType(Object renderType) {
		setField(this.renderType, renderType);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}

	@Override
	public int getMethod() {
		return getFieldValue(method);
	}
	
	@Override
	public void setMethod(int method) {
		setField(this.method, method);
	}
}
