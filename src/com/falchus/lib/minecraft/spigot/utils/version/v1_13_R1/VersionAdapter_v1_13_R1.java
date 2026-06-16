package com.falchus.lib.minecraft.spigot.utils.version.v1_13_R1;

import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1.VersionAdapter_v1_9_R1;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;

public class VersionAdapter_v1_13_R1 extends VersionAdapter_v1_9_R1 {
	
    @Override
    public Method entity_setCustomName() {
    	return ReflectionUtils.getMethod(entity, "setCustomName",
    		iChatBaseComponent
    	);
    }
	
    @Override
    public Method scoreboard_registerObjective() {
    	return ReflectionUtils.getFirstMethod(scoreboard,
			List.of(
				String.class,
				iScoreboardCriteria,
				iChatBaseComponent,
				iScoreboardCriteria$enumScoreboardHealthDisplay
			),
			"registerObjective",
			"addObjective"
		);
    }

	private Method player_setPlayerListHeaderFooter() {
		return ReflectionUtils.getMethod(Player.class, "setPlayerListHeaderFooter",
			String.class,
			String.class
		);
	}
	protected Method scoreboardTeam_setDisplayName() {
    	return ReflectionUtils.getMethod(scoreboardTeam, "setDisplayName",
			iChatBaseComponent
    	);
    }
	protected Method scoreboardTeam_setPrefix() {
    	return ReflectionUtils.getFirstMethod(scoreboardTeam,
    		List.of(
				iChatBaseComponent
    		),
    		"setPrefix",
    		"setPlayerPrefix"
    	);
    }
	protected Method scoreboardTeam_setSuffix() {
    	return ReflectionUtils.getFirstMethod(scoreboardTeam,
    		List.of(
				iChatBaseComponent
    		),
    		"setSuffix",
    		"setPlayerSuffix"
    	);
    }
	
	private Class<?> gameRule() {
		return ReflectionUtils.getClass(packageOb + "GameRule");
	}
	private Method gameRule_getByName() {
		return ReflectionUtils.getMethod(gameRule(), "getByName",
			String.class
		);
	}
	private Method gameRule_getType() {
		return ReflectionUtils.getMethod(gameRule(), "getType");
	}
	private Method world_setGameRule() {
		return ReflectionUtils.getMethod(world, "setGameRule",
			gameRule(),
			Object.class
		);
	}
	
    @Override
    public void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
    	try {
    	    String headerText = header != null ? String.join("\n", header) : "";
    	    String footerText = footer != null ? String.join("\n", footer) : "";
            
            player_setPlayerListHeaderFooter().invoke(player,
            	headerText,
            	footerText
            );
            player.setPlayerListName(name);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
    	try {
    		Object rule = gameRule_getByName().invoke(null,
    			gameRule.getKey()
    		);
    		if (rule == null) return;
    		
    		Class<?> type = (Class<?>) gameRule_getType().invoke(rule);
    		Object val = value;
    		if (type == Boolean.class) {
    			val = Boolean.parseBoolean(value);
    		} else if (type == Integer.class) {
    			val = Integer.parseInt(value);
    		}
    		world_setGameRule().invoke(world,
    			rule,
    			val
    		);
    	} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
}
