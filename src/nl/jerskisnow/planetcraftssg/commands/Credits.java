package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class Credits implements CommandExecutor {

	Main plugin;

	public Credits(Main mainClass) {
		plugin = mainClass;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.dataManager.isAdmin((Player) sender) && args.length != 0) {
			if (args.length > 1) {
				if (args[0].equalsIgnoreCase("add")) {
					if (args.length == 3) {
						OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);
						plugin.dataManager.addCredits(mentionedPlayer, Double.parseDouble(args[2]));
						sender.sendMessage(CFMessages.CreditsHasBeenAdded(args[2]));
					} else {
						this.SendHelp(sender);
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					if (args.length == 3) {
						OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);
						if (plugin.dataManager.getCredits(mentionedPlayer.getUniqueId()) >= Integer.parseInt(args[2])) {
							plugin.dataManager.removeCredits(mentionedPlayer, Double.parseDouble(args[2]));
							sender.sendMessage(CFMessages.CreditsHasBeenRemoved(args[2]));
						} else {
							sender.sendMessage(CFMessages.NotEnoughCredits(mentionedPlayer.getName()));
						}
					} else {
						this.SendHelp(sender);
					}
				} else if (args[0].equalsIgnoreCase("set")) {
					if (args.length == 3) {
						OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);
						plugin.dataManager.setCredits(mentionedPlayer, Double.parseDouble(args[2]));
						sender.sendMessage(CFMessages.CreditsHasBeenSet(mentionedPlayer.getName(), args[2]));
					} else {
						this.SendHelp(sender);
					}
				} else if (args[0].equalsIgnoreCase("info")) {
					if (args.length == 2) {
						OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);
						sender.sendMessage(CFMessages.CreditsInfoAdmin(mentionedPlayer.getName(),
								plugin.dataManager.getCredits(mentionedPlayer.getUniqueId()).toString()));
					} else {
						this.SendHelp(sender);
					}
				} else {
					this.SendHelp(sender);
				}
			} else {
				this.SendHelp(sender);
			}
		} else {
			sender.sendMessage(
					CFMessages.CreditsInfo(plugin.dataManager.getCredits(((Player) sender).getUniqueId()).toString()));
		}
		return true;
	}

	private void SendHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "/credits add <PlayerName> <Amount>");
		sender.sendMessage(ChatColor.RED + "/credits remove <PlayerName> <Amount>");
		sender.sendMessage(ChatColor.RED + "/credits set <PlayerName> <Amount>");
		sender.sendMessage(ChatColor.RED + "/credits info <PlayerName>");
	}

}
