package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;
import com.falchus.lib.minecraft.spigot.wrapper.network.syncher.DataWatcher;
import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class EntityWrapper extends SpigotWrapper implements Entity { // TODO
	
	Field id;
	Field yaw;
	Field pitch;
	Field boundingBox;
	Field dataWatcher;
	
	Method setLocation;
	Method isInvisible;
	Method setInvisible;
	Method getCustomName;
	Method setCustomName;
	Method isCustomNameVisible;
	Method setCustomNameVisible;
	Method getBukkitEntity;

	EntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getField("id");
		yaw = getFirstField(
			"yaw",
			"yRot"
		);
		pitch = getFirstField(
			"pitch",
			"xRot"
		);
		boundingBox = getFirstField(
			"boundingBox",
			"bb"
		);
		dataWatcher = getFirstField(
			"datawatcher",
			"entityData"
		);
		
		setLocation = getMethod("setLocation",
			double.class,
			double.class,
			double.class,
			float.class,
			float.class
		);
		isInvisible = getMethod("isInvisible");
		setInvisible = getMethod("setInvisible",
			boolean.class
		);
		getCustomName = getMethod("getCustomName");
		setCustomName = VersionProvider.get().entity_setCustomName();
		isCustomNameVisible = getMethod("isCustomNameVisible");
		setCustomNameVisible = getMethod("setCustomNameVisible",
			boolean.class
		);
		getBukkitEntity = getMethod("getBukkitEntity");
	}
	
	@Override
	public int getId() {
		return getFieldValue(id);
	}
	
	@Override
	public void setId(int id) {
		setField(this.id, id);
	}
	
	@Override
	public float getYaw() {
		return getFieldValue(yaw);
	}
	
	@Override
	public void setYaw(float yaw) {
		setField(this.yaw, yaw);
	}
	
	@Override
	public float getPitch() {
		return getFieldValue(pitch);
	}
	
	@Override
	public void setPitch(float pitch) {
		setField(this.pitch, pitch);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox() {
		return wrap(getFieldValue(boundingBox));
	}
	
	@Override
	public void setBoundingBox(AxisAlignedBB boundingBox) {
		setField(this.boundingBox, boundingBox.getHandle());
	}
	
	@Override
	public DataWatcher getDataWatcher() {
		return wrap(getFieldValue(dataWatcher));
	}
	
	@Override
	public void setLocation(double x, double y, double z, float yaw, float pitch) {
		try {
			setLocation.invoke(handle,
				x,
				y,
				z,
				yaw,
				pitch
			);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public boolean isInvisible() {
		try {
			return (boolean) isInvisible.invoke(handle);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void setInvisible(boolean invisible) {
		try {
			setInvisible.invoke(handle,
				invisible
			);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public Object getCustomName() {
		try {
			return getCustomName.invoke(handle);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void setCustomName(@NonNull String name) {
		try {
			if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
				setCustomName.invoke(handle,
					name
				);
			} else {
				setCustomName.invoke(handle,
					new WrappedComponent(name).getHandle()
				);
			}
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public boolean isCustomNameVisible() {
		try {
			return (boolean) isCustomNameVisible.invoke(handle);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void setCustomNameVisible(boolean visible) {
		try {
			setCustomNameVisible.invoke(handle,
				visible
			);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public org.bukkit.entity.Entity getBukkitEntity() {
		try {
			return (org.bukkit.entity.Entity) getBukkitEntity.invoke(handle);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
