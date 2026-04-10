package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityEffect extends PacketEntityWrapper {

	Field effectAmplifier;
	Field effectDurationTicks;
	Field flags;
	
	WrappedPacketOutEntityEffect(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityEffect",
				networkProtocolGame + "PacketPlayOutEntityEffect"
			)
		);
		
		effectAmplifier = getFirstField(
			"effectAmplifier",
			"c"
		);
		effectDurationTicks = getFirstField(
			"effectDurationTicks",
			"d"
		);
		flags = getFirstField(
			"flags",
			"e"
		);
	}

	public byte getEffectAmplifier() {
		return getFieldValue(effectAmplifier);
	}
	
	public void setEffectAmplifier(byte effectAmplifier) {
		setField(this.effectAmplifier, effectAmplifier);
	}

	public int getEffectDurationTicks() {
		return getFieldValue(effectDurationTicks);
	}
	
	public void setEffectDurationTicks(int effectDurationTicks) {
		setField(this.effectDurationTicks, effectDurationTicks);
	}

	public byte getFlags() {
		return getFieldValue(flags);
	}
	
	public void setFlags(byte flags) {
		setField(this.flags, flags);
	}
}
