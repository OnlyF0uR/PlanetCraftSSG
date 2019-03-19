package nl.jerskisnow.planetcraftssg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;
import nl.jerskisnow.planetcraftssg.utils.ScoreboardManager;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class PlayerJoin implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(CFMessages.PlayerJoin(e.getPlayer().getName()));
		if (!plugin.dataManager.isRegisteredPlayer(e.getPlayer().getUniqueId())) {
			plugin.dataManager.saveNewPlayer(e.getPlayer().getUniqueId());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:recipe give " + e.getPlayer().getName() + " *");
			Vault.econ.depositPlayer(e.getPlayer(), plugin.fileManager.getConfig("Config.yml").get().getDouble("StartersOptions.Coins"));
		}
		plugin.dataManager.initiatePlayTimeProcess(e.getPlayer().getUniqueId());

		ScoreboardManager.startScoreboard(e.getPlayer());
	}

}
