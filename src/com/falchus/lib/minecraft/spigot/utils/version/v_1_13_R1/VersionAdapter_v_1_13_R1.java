package com.falchus.lib.minecraft.spigot.utils.version.v_1_13_R1;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapter;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;

public class VersionAdapter_v_1_13_R1 extends VersionAdapter {

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
    public void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix) {
		try {
			Set<String> players = Set.of(player.getName());
			
			Object team = new ClassInstanceBuilder(
				scoreboardTeam
			).withParams(
				Map.of(
					scoreboard,
					scoreboardINST
				),
				Map.of(
					String.class,
					player.getName()
				)
			).build();
			scoreboardTeam_setDisplayName().invoke(team,
				createChatComponentText(player.getName())
			);
			scoreboardTeam_setPrefix().invoke(team,
				createChatComponentText(prefix)
			);
			scoreboardTeam_setSuffix().invoke(team,
				createChatComponentText(suffix)
			);
			
	        Object createPacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).withParams(
        		Map.of(
        			scoreboardTeam,
        			team
        		),
				Map.of(
					Collection.class,
					players
				),
				Map.of(
					int.class,
					0
				)
	        ).build();

	        Object updatePacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).withParams(
        		Map.of(
        			scoreboardTeam,
        			team
        		),
				Map.of(
					Collection.class,
					players
				),
				Map.of(
					int.class,
					2
				)
	        ).build();
	        
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	sendPacket(onlinePlayer, createPacket);
	        	sendPacket(onlinePlayer, updatePacket);
	        }
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
    
    @Override
    public void removeNametag(@NonNull Player player) {
		try {
			Set<String> players = Set.of(player.getName());
			
			Object team = new ClassInstanceBuilder(
				scoreboardTeam
			).withParams(
				Map.of(
					scoreboard,
					scoreboardINST
				),
				Map.of(
					String.class,
					player.getName()
				)
			).build();
			scoreboardTeam_setDisplayName().invoke(team,
				createChatComponentText(player.getName())
			);
			
	        Object removePacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).withParams(
	        	Map.of(
	        		scoreboardTeam,
	        		team
	        	),
	        	Map.of(
	        		Collection.class,
	        		players
	        	),
	        	Map.of(
	        		int.class,
	        		4
	        	)
	        ).build();
			
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	sendPacket(onlinePlayer, removePacket);
	        }
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
}
