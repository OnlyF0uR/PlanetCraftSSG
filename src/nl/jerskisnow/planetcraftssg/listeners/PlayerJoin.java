package nl.jerskisnow.planetcraftssg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;
import nl.jerskisnow.planetcraftssg.utils.ScoreboardManager;

public class PlayerJoin implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(CFMessages.PlayerJoin(e.getPlayer().getName()));
		if (!plugin.dataManager.isRegisteredPlayer(e.getPlayer().getUniqueId())) {
			plugin.dataManager.saveNewPlayer(e.getPlayer().getUniqueId());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:recipe add " + e.getPlayer().getName() + "*");
		}
		plugin.dataManager.initiatePlayTimeProcess(e.getPlayer().getUniqueId());

		ScoreboardManager.startScoreboard(e.getPlayer());
	}

}
