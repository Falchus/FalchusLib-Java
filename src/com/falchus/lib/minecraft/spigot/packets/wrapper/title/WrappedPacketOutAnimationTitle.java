package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutAnimationTitle extends PacketTitleWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutTitle",
		networkProtocolGame + "ClientboundSetTitlesAnimationPacket"
	);

	Field fadeInTime;
	Field stayTime;
	Field fadeOutTime;
	
	private WrappedPacketOutAnimationTitle(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutTitle",
			networkProtocolGame + "ClientboundSetTitlesAnimationPacket"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		
		fadeInTime = getFirstField(
			"fadeInTime",
			"fadeIn",
			"c"
		);
		stayTime = getFirstField(
			"stayTime",
			"stay",
			"d"
		);
		fadeOutTime = getFirstField(
			"fadeOutTime",
			"fadeOut",
			"e"
		);
	}
	
	public WrappedPacketOutAnimationTitle(int fadeInTicks, int stayTicks, int fadeOutTicks) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				fadeInTicks
			),
			Map.of(
				int.class,
				stayTicks
			),
			Map.of(
				int.class,
				fadeOutTicks
			)
		).build());
	}

	public int getFadeInTime() {
		return getFieldValue(fadeInTime);
	}
	
	public void setFadeInTime(int fadeInTime) {
		setField(this.fadeInTime, fadeInTime);
	}

	public int getStayTime() {
		return getFieldValue(stayTime);
	}
	
	public void setStayTime(int stayTime) {
		setField(this.stayTime, stayTime);
	}

	public int getFadeOutTime() {
		return getFieldValue(fadeOutTime);
	}
	
	public void setFadeOutTime(int fadeOutTime) {
		setField(this.fadeOutTime, fadeOutTime);
	}
}
