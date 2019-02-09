package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class StaffChat implements CommandExecutor {

	Main plugin;

	public StaffChat(Main mainClass) {
		plugin = mainClass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (plugin.dataManager.isStaff((Player) sender)) {
			if (args.length == 0) {
				plugin.dataManager.toggleStaffChat((Player) sender);
			} else {
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					if (plugin.dataManager.isStaff(onlinePlayer)) {
						onlinePlayer.sendMessage(
								CFMessages.StaffChatFormat(sender.getName(), plugin.dataManager.argsToString(args)));
					}
				}
			}
		} else {
			sender.sendMessage(CFMessages
					.NoPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Staff")));
		}
		return true;
	}

}
