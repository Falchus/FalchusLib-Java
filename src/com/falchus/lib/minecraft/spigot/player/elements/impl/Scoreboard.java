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
		update(title, titleColor, titleSecondColor, lines);
	}

	/**
	 * Updates the scoreboard periodically with dynamic content.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> titleSupplier, @NonNull Supplier<String> titleColorSupplier, Supplier<String> titleSecondColorSupplier, @NonNull Supplier<List<String>> linesSupplier) {
		super.sendUpdating(intervalTicks, () -> {
			String title = titleSupplier.get();
			String titleColor = titleColorSupplier.get();
			String titleSecondColor = titleSecondColorSupplier.get();
			List<String> lines = linesSupplier.get();
			update(title, titleColor, titleSecondColor, lines);
		});
	}
	
	public void remove() {
		super.remove();
		
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
	}
	
	private void update(@NonNull String title, @NonNull String titleColor, String titleSecondColor, @NonNull List<String> lines) {
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
	        
	        String prefix = line.length() > 16 ? line.substring(0, 16) : line;
	        String suffix = line.length() > 16 ? ChatColor.getLastColors(prefix) + line.substring(16) : "";
	        team.setPrefix(prefix);
	        team.setSuffix(suffix);

	        objective.getScore(entry).setScore(score);
	        score--;
	    }
	}
	
    private String getTitle(@NonNull String title, @NonNull String titleColor, String titleSecondColor) {
    	title = ChatColor.stripColor(title);
    	
        if (titleSecondColor == null) return titleColor + title;

        int length = title.length();
        int cycleLength = length * 2;
        int pos = frame % length;
        
        StringBuilder sb = new StringBuilder();

        if (frame < length) {
            sb.append(titleSecondColor);
            sb.append(title.substring(0, pos + 1));
            sb.append(titleColor);
            sb.append(title.substring(pos + 1));
        } else {
            sb.append(titleColor);
            sb.append(title.substring(0, pos + 1));
            sb.append(titleSecondColor);
            sb.append(title.substring(pos + 1));
        }

        frame = (frame + 1) % cycleLength;

        return sb.toString();
    }
}
