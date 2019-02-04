package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class PlayTime implements CommandExecutor {

	Main plugin;

	public PlayTime(Main mainClass) {
		plugin = mainClass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!plugin.dataManager.isStaff((Player) sender)) {
			sender.sendMessage(CFMessages.OwnPlayTime(plugin.dataManager.getPlayedTime(((Player) sender).getUniqueId())));
		} else {
			if (args.length == 0) {
				sender.sendMessage(
						CFMessages.OwnPlayTime(plugin.dataManager.getPlayedTime(((Player) sender).getUniqueId())));
			} else {
				@SuppressWarnings("deprecation")
				OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[0]);
				if (plugin.dataManager.isRegisteredPlayer(mentionedPlayer.getUniqueId())) {
					sender.sendMessage(CFMessages.PlayerPlayTime(mentionedPlayer.getName(),
							plugin.dataManager.getPlayedTime(mentionedPlayer.getUniqueId())));
				} else {
					sender.sendMessage(CFMessages.PlayerDoesNotExists);
				}
			}
		}
		return true;
	}

}
