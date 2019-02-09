package nl.jerskisnow.planetcraftssg.utils;

import org.bukkit.ChatColor;

import nl.jerskisnow.planetcraftssg.Main;

public class CFMessages {
	
	// Create an instance to the main
	private static Main plugin = Main.getPlugin(Main.class);
	
	/*
	 * NoPermission message and other player related messages
	 */
	public static String NoPermission(String permission) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("NoPermission").replaceAll("<Permission>", permission));
	}
	public static String PlayerDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("PlayerDoesNotExists"));
	
	/*
	 * Player join & Player Leave
	 */
	public static String PlayerJoin(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerJoin").replaceAll("<PlayerName>", playername));
	}
	public static String PlayerQuit(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerQuit").replaceAll("<PlayerName>", playername));
	}
	
	/*
	 * (Staff)Chat
	 */
	public static String ChatFormat(String country, Integer level, String playername, String message) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("ChatFormat")
						.replaceAll("<Country>", country)
				    	.replaceAll("<Level>", level.toString())
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Message>", message));
	}
	public static String StaffChatFormat(String playername, String message) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("StaffChat.Format")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Message>", message));
	}
	public static String EnteredStaffChat = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("StaffChat.EnteredChannel"));
	public static String LeftStaffChat = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("StaffChat.LeftChannel"));
	
	/*
	 * PlayTime messages
	 */
	public static String OwnPlayTime(String playtime) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("OwnPlayTime")
						.replaceAll("<Time>", playtime));
	}

	public static String PlayerPlayTime(String playername, String playtime) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerPlayTime")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Time>", playtime));
	}
	/*
	 * Credits messages
	 */
	public static String CreditsHasBeenAdded(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenAdded")
						.replaceAll("<Credits>", credits));
	}
	public static String CreditsHasBeenRemoved(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenRemoved")
						.replaceAll("<Credits>", credits));
	}
	public static String NotEnoughCredits(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.NotEnough")
						.replaceAll("<PlayerName>", playername));
	}
	public static String CreditsHasBeenSet(String playername, String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenSet")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Credits>", credits));
	}
	public static String CreditsInfoAdmin(String playername, String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.InfoAdmin")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Credits>", credits));
	}
	public static String CreditsInfo(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.Info")
						.replaceAll("<Credits>", credits));
	}
	
	/*
	 * Country command messages
	 */
	public static String NoSelectionFound = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.NoSelectionFound"));
	public static String CountryDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.DoesNotExists"));
	public static String CountryAlreadyExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.AlreadyExists"));
	public static String CountryHasBeenCreated(String country) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.HasBeenCreated")
						.replaceAll("<Country>", country));
	}
	public static String CountryHasBeenRemoved(String country) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.HasBeenRemoved")
						.replaceAll("<Country>", country));
	}
	public static String AddedPlayerToCountry(String playername, String country, String role) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.AddedPlayer")
						.replaceAll("<PlayerNme>", playername)
				    	.replaceAll("<Country>", country)
						.replaceAll("<Role>", role));
	}
	public static String RemovedPlayerFromCountry(String playername, String country, String role) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.RemovedPlayer")
						.replaceAll("<PlayerNme>", playername)
				    	.replaceAll("<Country>", country)
						.replaceAll("<Role>", role));
	}
	public static String InvalidCountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.Invalid"));
	public static String ClearedCountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.HasBeenCleared"));

	public static String CMDUsageError(String commandusage) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("CommandUsageError")
						.replaceAll("<CMDUsage>", commandusage));
	}
	public static String ReportedPlayer(String reportid) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("ReportedPlayer")
						.replaceAll("<ReportID>", reportid));
	}
	
	public static String ReportDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("ReportDoesNotExists"));
	
	public static String NoReportsFound = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("NoReportsFound"));
	

}
