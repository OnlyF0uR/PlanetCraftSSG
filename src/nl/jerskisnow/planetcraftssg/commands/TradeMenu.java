package nl.jerskisnow.planetcraftssg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class TradeMenu implements CommandExecutor {
	
	Main plugin;

	public TradeMenu(Main mainClass) {
		plugin = mainClass;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (sender.hasPermission("planetcraftssg.trademenucommand")) {
			nl.jerskisnow.planetcraftssg.trading.TradeMenu.openTradeMenu((Player) sender);
		} else {
			sender.sendMessage(CFMessages.NoPermission("planetcraftssg.trademenucommand"));
		}
		return true;
	}

}
