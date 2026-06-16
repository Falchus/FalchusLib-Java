package com.falchus.lib.minecraft.spigot.utils.version.v1_20_R4;

import java.lang.reflect.Method;

import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapterModern;
import com.falchus.lib.utils.reflection.ReflectionUtils;

public class VersionAdapter_v1_20_R4 extends VersionAdapterModern {
	
    @Override
    public Method scoreboard_registerObjective() {
    	return ReflectionUtils.getMethod(scoreboard, "addObjective",
			String.class,
			iScoreboardCriteria,
			iChatBaseComponent,
			iScoreboardCriteria$enumScoreboardHealthDisplay,
			boolean.class,
			numberFormat()
    	);
    }
    
    private Class<?> numberFormat() {
    	return ReflectionUtils.getClass(packageNm + "network.chat.numbers.NumberFormat");
    }
}
