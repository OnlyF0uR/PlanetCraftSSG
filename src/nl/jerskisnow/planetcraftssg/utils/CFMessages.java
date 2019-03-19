package nl.jerskisnow.planetcraftssg.utils;

import org.bukkit.ChatColor;

import nl.jerskisnow.planetcraftssg.Main;

public class CFMessages {
	
	// Create an instance to the main
	private static Main plugin = Main.getPlugin(Main.class);
	
	/*
	 * NoPermission message and other player related messages
	 */
	public static final String NoPermission(String permission) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("NoPermission").replaceAll("<Permission>", permission));
	}
	public static final String PlayerDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("PlayerDoesNotExists"));
	
	/*
	 * Player join & Player Leave
	 */
	public static final String PlayerJoin(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerJoin").replaceAll("<PlayerName>", playername));
	}
	public static final String PlayerQuit(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerQuit").replaceAll("<PlayerName>", playername));
	}
	
	/*
	 * (Staff)Chat
	 */
	public static final String ChatFormat(String country, Integer level, String playername, String displayname, String chatcolor, String message) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("ChatFormat")
						.replaceAll("<Country>", country)
				    	.replaceAll("<Level>", level.toString())
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<DisplayName>", displayname)
						.replaceAll("<ChatColor>", chatcolor)
						.replaceAll("<Message>", message));
	}
	public static final String StaffChatFormat(String playername, String message) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("StaffChat.Format")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Message>", message));
	}
	public static final String EnteredStaffChat = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("StaffChat.EnteredChannel"));
	public static String LeftStaffChat = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("StaffChat.LeftChannel"));
	
	/*
	 * PlayTime messages
	 */
	public static final String OwnPlayTime(String playtime) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("OwnPlayTime")
						.replaceAll("<Time>", playtime));
	}

	public static final String PlayerPlayTime(String playername, String playtime) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("PlayerPlayTime")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Time>", playtime));
	}
	/*
	 * Credits messages
	 */
	public static final String CreditsHasBeenAdded(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenAdded")
						.replaceAll("<Credits>", credits));
	}
	public static final String CreditsHasBeenRemoved(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenRemoved")
						.replaceAll("<Credits>", credits));
	}
	public static final String NotEnoughCredits(String playername) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.NotEnough")
						.replaceAll("<PlayerName>", playername));
	}
	public static final String CreditsHasBeenSet(String playername, String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.HasBeenSet")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Credits>", credits));
	}
	public static final String CreditsInfoAdmin(String playername, String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.InfoAdmin")
						.replaceAll("<PlayerName>", playername)
						.replaceAll("<Credits>", credits));
	}
	public static final String CreditsInfo(String credits) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Credits.Info")
						.replaceAll("<Credits>", credits));
	}
	
	/*
	 * Country command messages
	 */
	public static final String NoSelectionFound = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.NoSelectionFound"));
	public static final String CountryDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.DoesNotExists"));
	public static final String CountryAlreadyExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.AlreadyExists"));
	public static final String CountryHasBeenCreated(String country) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.HasBeenCreated")
						.replaceAll("<Country>", country));
	}
	public static final String CountryHasBeenRemoved(String country) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.HasBeenRemoved")
						.replaceAll("<Country>", country));
	}
	public static final String AddedPlayerToCountry(String playername, String country, String role) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.AddedPlayer")
						.replaceAll("<PlayerNme>", playername)
				    	.replaceAll("<Country>", country)
						.replaceAll("<Role>", role));
	}
	public static final String RemovedPlayerFromCountry(String playername, String country, String role) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("Country.RemovedPlayer")
						.replaceAll("<PlayerNme>", playername)
				    	.replaceAll("<Country>", country)
						.replaceAll("<Role>", role));
	}
	public static final String InvalidCountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.Invalid"));
	public static final String ClearedCountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.HasBeenCleared"));

	public static final String CMDUsageError(String commandusage) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("CommandUsageError")
						.replaceAll("<CMDUsage>", commandusage));
	}
	public static final String ReportedPlayer(String reportid) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("ReportedPlayer")
						.replaceAll("<ReportID>", reportid));
	}
	
	public static final String ReportDoesNotExists = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("ReportDoesNotExists"));
	
	public static final String NoReportsFound = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("NoReportsFound"));
	
	public static final String WrongTool = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("WrongTool"));
	
	public static final String NotAMemberOfACountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.NoNationality"));
	
	public static final String LeftCountry = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("Country.Left"));
	
	public static final String ActiveCooldown = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("ActiveCooldown"));
	
	public static final String ChangedChatColor(String chatcolor) {
		return ChatColor.translateAlternateColorCodes('&',
				plugin.dataManager.getMessage("ChangedChatColor")
						.replaceAll("<ChatColor>", chatcolor));
	}
	
	public static final String InvalidChatColor = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("InvalidChatColor"));
	
	public static final String EnteredStaffMode = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("EnteredStaffMode"));
	
	public static final String LeftStaffMode = ChatColor.translateAlternateColorCodes('&',
			plugin.dataManager.getMessage("LeftStaffMode"));

}
