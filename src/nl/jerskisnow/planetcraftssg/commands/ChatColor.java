package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class ChatColor implements CommandExecutor {
	
	Main plugin;

	public ChatColor(Main mainClass) {
		plugin = mainClass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (!sender.hasPermission("planetcraftssg.chatcolor")) {
			sender.sendMessage(CFMessages.NoPermission("planetcraftssg.chatcolor"));
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(CFMessages.CMDUsageError("/chatcolor <ColorCode>"));
			return true;
		}
		if (this.isValidChatColor(args[0])) {
			plugin.dataManager.setChatColor(((Player) sender).getUniqueId(), args[0]);
			sender.sendMessage(CFMessages.ChangedChatColor(args[0]));
		} else {
			sender.sendMessage(CFMessages.InvalidChatColor);
		}
		return true;
	}
	
	private boolean isValidChatColor(String str) {
		boolean outputBool = false;
		switch (str) {
		case "&0":
		case "&1":
		case "&2":
		case "&3":
		case "&4":
		case "&5":
		case "&6":
		case "&7":
		case "&8":
		case "&9":
		case "&a":
		case "&b":
		case "&c":
		case "&d":
		case "&e":
		case "&f":
			outputBool = true;
			break;
		default:
			break;
		}
		return outputBool;
	}

}
