package nl.jerskisnow.planetcraftssg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class PlayerChat implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (plugin.dataManager.isInStaffChat(e.getPlayer())) {
			e.setCancelled(true);
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				if (plugin.dataManager.isStaff(onlinePlayer)) {
					onlinePlayer.sendMessage(CFMessages.StaffChatFormat(e.getPlayer().getName(), e.getMessage()));
				}
			}
		}
		e.setFormat(CFMessages.ChatFormat(plugin.dataManager.getCountry(e.getPlayer().getUniqueId()),
				plugin.dataManager.getLevel(e.getPlayer().getUniqueId()), e.getPlayer().getName(), e.getMessage()));
	}
}
