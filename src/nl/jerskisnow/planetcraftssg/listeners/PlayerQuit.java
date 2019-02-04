package nl.jerskisnow.planetcraftssg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class PlayerQuit implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(CFMessages.PlayerQuit(e.getPlayer().getName()));
		plugin.dataManager.endPlayTimeProcess(e.getPlayer().getUniqueId());
		plugin.dataManager.forceRemoveStaffChat(e.getPlayer());
	}

}
