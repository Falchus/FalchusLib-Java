package com.falchus.lib.minecraft.spigot.utils.version.v1_15_R1;

import java.lang.reflect.Method;

import org.bukkit.entity.Damageable;

import com.falchus.lib.minecraft.spigot.utils.version.v_1_13_R1.VersionAdapter_v_1_13_R1;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;

public class VersionAdapter_v_1_15_R1 extends VersionAdapter_v_1_13_R1 {
	
	private Method damageable_getAbsorptionAmount() {
		return ReflectionUtils.getMethod(Damageable.class, "getAbsorptionAmount");
	}
	private Method damageable_setAbsorptionAmount() {
		return ReflectionUtils.getMethod(Damageable.class, "setAbsorptionAmount",
			double.class
		);
	}
	
    @Override
    public double getAbsorption(@NonNull Damageable entity) {
    	try {
    		return (double) damageable_getAbsorptionAmount().invoke(entity);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setAbsorption(@NonNull Damageable entity, double absorption) {
    	try {
    		damageable_setAbsorptionAmount().invoke(entity,
    			absorption
    		);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
