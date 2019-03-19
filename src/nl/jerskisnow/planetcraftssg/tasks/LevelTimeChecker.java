package nl.jerskisnow.planetcraftssg.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.*;

import nl.jerskisnow.planetcraftssg.Main;

public class LevelTimeChecker extends BukkitRunnable {
	
	private Main plugin = Main.getPlugin(Main.class);
    
    public void run() {
    	for (Player player: Bukkit.getOnlinePlayers()) {
    		
    		// Update the playertime
    		plugin.dataManager.endPlayTimeProcess(player.getUniqueId());
    		plugin.dataManager.initiatePlayTimeProcess(player.getUniqueId());
    		
    		// Get the total amount of hours
    		int hours = (plugin.dataManager.getPlayedDays(player.getUniqueId()) * 24) + plugin.dataManager.getPlayedHours(player.getUniqueId());
    		
    		// Define the old and current level of the player
    		int oldLevel = plugin.dataManager.getLevel(player.getUniqueId());
    		// Define the new level according to the amount of hours
    		int newLevel = (hours / plugin.fileManager.getConfig("Config.yml").get().getInt("AmountOfHoursPerLevel")) + plugin.fileManager.getConfig("Config.yml").get().getInt("StartersOptions.Level");
    		
    		// Check if the new level is not equal to the old level, if it is not equal it will reset the level
    		if (newLevel != oldLevel) {
    			plugin.dataManager.setLevel(player.getUniqueId(), newLevel);
    		}
    	}
    }
}
