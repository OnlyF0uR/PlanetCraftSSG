package nl.jerskisnow.planetcraftssg.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiPlayerReload implements Listener {

	@EventHandler
	public void onReload(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("reload") || e.getMessage().equalsIgnoreCase("rl")) {
			e.getPlayer().sendMessage(ChatColor.RED + "Please restart the server instead of reloading it!");
			e.setCancelled(true);
		}
	}

}
