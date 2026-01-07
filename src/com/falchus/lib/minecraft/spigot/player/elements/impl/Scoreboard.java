package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;

import lombok.NonNull;

public class Scoreboard extends PlayerElement {

	private final org.bukkit.scoreboard.Scoreboard scoreboard;
	private final Objective objective;
	private List<String> lines;
    private int frame = 0;
	
	private Scoreboard(@NonNull Player player) {
		super(player);
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("FalchusLib", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
	}
	
	/**
	 * Updates the scoreboard immediately.
	 */
	public void send(@NonNull String title, @NonNull String titleColor, String titleSecondColor, @NonNull List<String> lines) {
		if (this.lines == null || lines.size() != this.lines.size()) {
			for (Team team : scoreboard.getTeams()) {
				team.unregister();
			}
			for (String entry : scoreboard.getEntries()) {
				scoreboard.resetScores(entry);
			}
		}
		this.lines = lines;
		
	    objective.setDisplayName(getTitle(title, titleColor, titleSecondColor));
		
	    int score = lines.size();
	    for (String line : lines) {
	        if (line.isEmpty()) {
	        	line = "§r";
	        } else if (line.length() > 32) {
	            line = line.substring(0, 32);
	        }
	
	        String teamName = getClass().getSimpleName() + "_" + score;
	        Team team = scoreboard.getTeam(teamName);
	        if (team == null) {
	        	team = scoreboard.registerNewTeam(teamName);
	        }
	        
	        String entry = "§" + Integer.toHexString(score);
	        if (!team.hasEntry(entry)) {
	        	team.addEntry(entry);
	        }
	        
	        int maxLength = 16;
	        String prefix = line;
	        String suffix = "";
	        if (prefix.length() > 16) {
	        	int index = line.charAt(maxLength - 1) == ChatColor.COLOR_CHAR
	        			? (maxLength - 1) : maxLength;
	        	prefix = line.substring(0, index);
	        	String suffixTmp = line.substring(index);
	        	ChatColor chatColor = null;
	        	
	        	if (suffixTmp.length() >= 2 && suffixTmp.charAt(0) == ChatColor.COLOR_CHAR) {
	        		chatColor = ChatColor.getByChar(suffixTmp.charAt(1));
	        	}
	        	
	        	String color = ChatColor.getLastColors(prefix);
	        	boolean addColor = chatColor == null || chatColor.isFormat();
	        	suffix = (addColor ? (color.isEmpty() ? ChatColor.RESET.toString() : color) : "") + suffixTmp;
	        }
	        team.setPrefix(prefix);
	        team.setSuffix(suffix);
	
	        objective.getScore(entry).setScore(score);
	        score--;
	    }
	}

	/**
	 * Updates the scoreboard periodically with dynamic content.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> title, @NonNull Supplier<String> titleColor, Supplier<String> titleSecondColor, @NonNull Supplier<List<String>> lines) {
		super.sendUpdating(intervalTicks, () ->
			send(
				title.get(),
				titleColor.get(),
				titleSecondColor != null ? titleSecondColor.get() : null,
				lines.get()
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	
    private String getTitle(@NonNull String title, @NonNull String titleColor, String titleSecondColor) {
    	title = ChatColor.stripColor(title);
    	if (title.isEmpty()) return "";
    	
        if (titleSecondColor == null) return titleColor + title;

        int length = title.length();
        int cycleLength = length * 2;
        int pos = frame % length;
        
        StringBuilder sb = new StringBuilder();

        if (frame < length) {
            sb.append(titleSecondColor)
            	.append(title.substring(0, pos + 1))
            	.append(titleColor)
            	.append(title.substring(pos + 1));
        } else {
            sb.append(titleColor)
            	.append(title.substring(0, pos + 1))
            	.append(titleSecondColor)
            	.append(title.substring(pos + 1));
        }

        frame = (frame + 1) % cycleLength;

        title = sb.toString();
    	if (title.length() > 32) {
    		title = title.substring(0, 32);
    	}
        return title;
    }
}
