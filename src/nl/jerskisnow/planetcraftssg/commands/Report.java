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

public class Report implements CommandExecutor {
	
	Main plugin;

	public Report(Main mainClass) {
		plugin = mainClass;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!plugin.dataManager.isStaff((Player) sender)) {
			if (args.length >= 2) {
				if (getOfflinePlayer(args[0]) == null) {
					sender.sendMessage(CFMessages.PlayerDoesNotExists);
					return true;
				}
				String reportReason = "";
				for (int i = 1; i != args.length; i++) {
					reportReason = reportReason + args[i] + " ";
				}
				plugin.dataManager.initiateReport(((Player) sender).getUniqueId(), getOfflinePlayer(args[0]).getUniqueId(), reportReason);
			} else {
				sender.sendMessage(CFMessages.CMDUsageError("/report <Player> <Reason>"));
			}
		} else {
			if (args.length >= 2 && args[0].equalsIgnoreCase("send")) {
				if (getOfflinePlayer(args[1]) == null) {
					sender.sendMessage(CFMessages.PlayerDoesNotExists);
					return true;
				}
				String reportReason = "";
				for (int i = 2; i != args.length; i++) {
					reportReason = reportReason + args[i] + " ";
				}
				plugin.dataManager.initiateReport(((Player) sender).getUniqueId(), getOfflinePlayer(args[1]).getUniqueId(), reportReason);
			} else if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
				if (!plugin.dataManager.reportExists(args[1])) {
					sender.sendMessage(CFMessages.ReportDoesNotExists);
					return true;
				}
				plugin.dataManager.sendReportInfo(sender, args[1]);
			} else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
				if (!plugin.dataManager.reportExists(args[1])) {
					sender.sendMessage(CFMessages.ReportDoesNotExists);
					return true;
				}
				plugin.dataManager.removeReport(args[1]);
			} else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
				plugin.dataManager.sendReportList(sender);
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/report send <Player> <Reason>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/report info <ReportID>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/report remove <ReportID>"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/report list"));
			}
			// check reports etc.
		}
		return true;
	}
	
//	private boolean offlinePlayerDoesExists(String playername) {
//		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
//			if (player.getName().equals(playername)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
    private OfflinePlayer getOfflinePlayer(String name) {
        for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if(player.getName().equals(name)) return player;
        }
        return null;
    }

}
