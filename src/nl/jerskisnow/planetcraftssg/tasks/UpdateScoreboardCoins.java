package nl.jerskisnow.planetcraftssg.tasks;

import org.bukkit.scheduler.*;

import nl.jerskisnow.planetcraftssg.utils.ScoreboardManager;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class UpdateScoreboardCoins extends BukkitRunnable {
    
    public void run() {
    	// Loop through all the players
    	for (Player p : Bukkit.getOnlinePlayers()) {
    		// Refresh the amount of coins in the scoreboard
    		ScoreboardManager.initiateCoins(p);
    	}
    }
}
