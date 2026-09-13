package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
<<<<<<< HEAD
import java.util.Map;
=======
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
import java.util.Set;

<<<<<<< HEAD
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
=======
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

<<<<<<< HEAD
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

=======
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityVelocity extends PacketEntityWrapper {
	
<<<<<<< HEAD
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutEntityVelocity",
		networkProtocolGame + "PacketPlayOutEntityVelocity"
	);
	
=======
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	Field x;
	Field y;
	Field z;
	
	private WrappedPacketOutEntityVelocity(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
		
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityVelocity",
			networkProtocolGame + "PacketPlayOutEntityVelocity"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		x = getFirstField(
			"xa",
			"b"
		);
		y = getFirstField(
			"ya",
			"c"
		);
		z = getFirstField(
			"za",
			"d"
		);
	}
	
<<<<<<< HEAD
	public WrappedPacketOutEntityVelocity(@NonNull Entity entity) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			)
		).build());
	}
	
=======
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	public int getX() {
		return getFieldValue(x);
	}
	
	public void setX(int x) {
		setField(this.x, x);
	}
	
	public int getY() {
		return getFieldValue(y);
	}
	
	public void setY(int y) {
		setField(this.y, y);
	}
	
	public int getZ() {
		return getFieldValue(z);
	}
	
	public void setZ(int z) {
		setField(this.z, z);
	}
}
