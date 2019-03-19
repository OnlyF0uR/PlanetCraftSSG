package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.connorlinfoot.titleapi.TitleAPI;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class StaffMode implements CommandExecutor {
	
	Main plugin;

	public StaffMode(Main mainClass) {
		plugin = mainClass;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player sPlayer = (Player) sender;
		if (plugin.dataManager.isStaff(sPlayer)) {
			if (!plugin.dataManager.isInStaffMode(sPlayer)) {
				plugin.dataManager.setStaffMode(sPlayer);
				Bukkit.broadcastMessage(CFMessages.PlayerQuit(sPlayer.getName()));
				if (sPlayer.getGameMode() != GameMode.CREATIVE && sPlayer.getGameMode() != GameMode.SPECTATOR) {
					sPlayer.setAllowFlight(true);
					sPlayer.setInvulnerable(true);	
				}
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					onlinePlayer.hidePlayer(plugin, sPlayer);
				}
				if (plugin.fileManager.getConfig("Config.yml").get().getBoolean("TitleAPI")) {
					TitleAPI.sendTitle(sPlayer, 20, 100, 20, "&cYou are now in", "&cStaffMode");
				} else {
					sPlayer.sendMessage(CFMessages.EnteredStaffMode);
				}
			} else {
				plugin.dataManager.removeStaffMode(sPlayer);
				Bukkit.broadcastMessage(CFMessages.PlayerJoin(sPlayer.getName()));
				if (sPlayer.getGameMode() != GameMode.CREATIVE && sPlayer.getGameMode() != GameMode.SPECTATOR) {
					sPlayer.setAllowFlight(false);
					sPlayer.setInvulnerable(false);	
				}
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					onlinePlayer.showPlayer(plugin, sPlayer);
				}
				if (plugin.fileManager.getConfig("Config.yml").get().getBoolean("TitleAPI")) {
					TitleAPI.sendTitle(sPlayer, 20, 100, 20, "&cYou are no longer in", "&cStaffMode");
				} else {
					sPlayer.sendMessage(CFMessages.LeftStaffMode);
				}
			}
		} else {
			sender.sendMessage(CFMessages.NoPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Staff")));
		}
		return true;
	}

}
