package nl.jerskisnow.planetcraftssg.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.connorlinfoot.titleapi.TitleAPI;
import com.sk89q.worldguard.WorldGuard;

import nl.jerskisnow.planetcraftssg.Main;

public class PlayerMovement implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
//		if(e.getFrom().getBlockX() == e.getTo().getBlockX()
//				&& e.getFrom().getBlockZ() == e.getTo().getBlockZ()
//				&& e.getFrom().getBlockY() == e.getTo().getBlockY()) {
			
			List<String> fromList = WorldGuard.getInstance().getPlatform()
					.getRegionContainer()
					.get(WorldGuard.getInstance().getPlatform().getWorldByName((e.getPlayer()).getWorld().getName()))
					.getApplicableRegionsIDs(plugin.dataManager.locationToVector(e.getFrom()));
			
			List<String> toList = WorldGuard.getInstance().getPlatform()
					.getRegionContainer()
					.get(WorldGuard.getInstance().getPlatform().getWorldByName((e.getPlayer()).getWorld().getName()))
					.getApplicableRegionsIDs(plugin.dataManager.locationToVector(e.getTo()));
			
			if (fromList.isEmpty() && !toList.isEmpty()) {
				System.out.println("Just walked into " + toList.get(0));
				if (plugin.fileManager.getConfig("Config.yml").get().getBoolean("TitleAPI")) {
					TitleAPI.sendTitle(e.getPlayer(), 20, 100, 20, "&3Now entering", toList.get(0));
				}
			} else if (!fromList.isEmpty() && toList.isEmpty()) {
				System.out.println("Just walked out of " + fromList.get(0));
				if (plugin.fileManager.getConfig("Config.yml").get().getBoolean("TitleAPI")) {
					TitleAPI.sendTitle(e.getPlayer(), 20, 100, 20, "&3Now Leaving", fromList.get(0));
				}
			} else if (!fromList.isEmpty() && !toList.isEmpty()) {
				if (fromList.get(0) != toList.get(0)) {
					System.out.println("Just walked from " + fromList.get(0) + " to " + toList.get(0));
					if (plugin.fileManager.getConfig("Config.yml").get().getBoolean("TitleAPI")) {
						TitleAPI.sendTitle(e.getPlayer(), 20, 100, 20, "&3Leaving / Entering", fromList.get(0) + " / " + toList.get(0));
					}
				}
			}
			
		}
//	}

}
