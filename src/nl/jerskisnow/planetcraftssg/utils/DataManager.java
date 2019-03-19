package nl.jerskisnow.planetcraftssg.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;

public class DataManager {

	private HashMap<UUID, Long> playerTime = new HashMap<>();

	private ArrayList<String> staffChat = new ArrayList<>();
	
	private ArrayList<String> staffMode = new ArrayList<>();
	
	private SimpleDateFormat datum = new SimpleDateFormat("dd-MM-yyyy");

	Main plugin;

	public DataManager(Main mainClass) {
		plugin = mainClass;
	}

	public void loadFiles() {
		plugin.fileManager.getConfig("Config.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("Messages.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("PlayerData.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("ReportData.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("AutoBroadcaster.yml").copyDefaults(true).save();
	}
	
	public void reloadFiles() {
		plugin.fileManager.getConfig("Config.yml").reload();
		plugin.fileManager.getConfig("Messages.yml").reload();
		plugin.fileManager.getConfig("PlayerData.yml").reload();
		plugin.fileManager.getConfig("ReportData.yml").reload();
		plugin.fileManager.getConfig("AutoBroadcaster.yml").reload();
	}

	public boolean isRegisteredPlayer(UUID userID) {
		if (plugin.fileManager.getConfig("PlayerData.yml").get().contains(userID.toString())) {
			return true;
		}
		return false;
	}

	public String getMessage(String msg) {
		return (String) plugin.fileManager.getConfig("Messages.yml").get().get(msg);
	}

	public void saveNewPlayer(UUID userID) {
		/*
		 * UUID:
		 *   Country: 'None'
		 *   Level: 1 
		 *   Credits: 0.0 
		 *   Time: 
		 *     Days: 0
		 *     Hours: 0
		 *     Minutes: 0 
		 *     Seconds: 0
		 *   ChatColor: '&7'
		 *   TimeUntillNextLevel: 12
		 */
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Country",
				plugin.fileManager.getConfig("Config.yml").get().getString("StartersOptions.Country"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Level",
				plugin.fileManager.getConfig("Config.yml").get().getInt("StartersOptions.Level"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Credits",
				plugin.fileManager.getConfig("Config.yml").get().getDouble("StartersOptions.Credits"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time", 0);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Days", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Hours", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Minutes", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Seconds", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".ChatColor",
				plugin.fileManager.getConfig("Config.yml").get().getString("StartersOptions.ChatColor"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".TimeUntillNextLevel", 12);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	/*
	 * Getters for the PlayerData file
	 */
	public String getCountry(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getString(userID + ".Country");
	}
	
	public Integer getLevel(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Level");
	}
	
	// ---
	public Double getCredits(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getDouble(userID + ".Credits");
	}

	public int getPlayedDays(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Days");
	}

	public int getPlayedHours(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Hours");
	}

	public int getPlayedMinutes(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Minutes");
	}

	public int getPlayedSeconds(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Seconds");
	}
	
	public String getChatColor(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getString(userID + ".ChatColor");
	}
	// ---

	public String getPlayedTime(UUID userID) {
		StringBuilder sb = new StringBuilder(64);
		int playedDays = this.getPlayedDays(userID);
		int playedHours = this.getPlayedHours(userID);
		int playedMinutes = this.getPlayedMinutes(userID);
		int playedSeconds = this.getPlayedSeconds(userID);
		sb.append(playedDays);
		if (playedDays == 1) {
			sb.append(" day, ");
		} else {
			sb.append(" days, ");
		}
		sb.append(playedHours);
		if (playedHours == 1) {
			sb.append(" hour, ");
		} else {
			sb.append(" hours, ");
		}
		sb.append(playedMinutes);
		if (playedMinutes == 1) {
			sb.append(" minute and, ");
		} else {
			sb.append(" minutes and, ");
		}
		sb.append(playedSeconds);
		if (playedMinutes == 1) {
			sb.append(" second");
		} else {
			sb.append(" seconds");
		}
		return sb.toString();
	}

	public boolean isInStaffChat(Player p) {
		return this.staffChat.contains(p.getName());
	}
	
	public boolean isInStaffMode(Player p) {
		return this.staffMode.contains(p.getName());
	}

	public boolean isAdmin(OfflinePlayer p) {
		return p.getPlayer().hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Admin"));
	}

	public boolean isStaff(OfflinePlayer p) {
		return p.getPlayer().hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Staff"));
	}

	public boolean isBuilder(OfflinePlayer p) {
		return p.getPlayer().hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Builder"));
	}

	public com.sk89q.worldedit.Vector locationToVector(Location loc) {
		return new com.sk89q.worldedit.Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	/*
	 * Setters for the PlayerData file
	 */
	public void setCountry(UUID userID, String newCountry) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Country", newCountry);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}
	public void setLevel(UUID userID, Integer newLevel) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Level", newLevel);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void addCredits(OfflinePlayer p, Double credits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Credits",
				this.getCredits(p.getUniqueId()) + credits);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void removeCredits(OfflinePlayer p, Double credits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Credits",
				this.getCredits(p.getUniqueId()) - credits);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void setCredits(OfflinePlayer p, Double newCredits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Credits", newCredits);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}
	
	public void setChatColor(UUID userID, String newChatColor) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".ChatColor", newChatColor);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void initiatePlayTimeProcess(UUID userID) {
		playerTime.put(userID, System.currentTimeMillis());
	}

	public void endPlayTimeProcess(UUID userID) {
		if (playerTime.get(userID) == null) {
			return;
		}
		// Current time minus the start time
		long milliseconds = System.currentTimeMillis() - playerTime.get(userID);
		long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
		milliseconds -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
		milliseconds -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

		int newDays = (int) (days + this.getPlayedDays(userID));

		int newHours = (int) (hours + this.getPlayedHours(userID));
		if (newHours > 23) {
			newDays++;
			newHours = newHours % 24;
		}
		int newMinutes = (int) (minutes + this.getPlayedMinutes(userID));
		if (newMinutes > 59) {
			newHours++;
			newMinutes = newMinutes % 60;
		}
		int newSeconds = (int) (seconds + this.getPlayedSeconds(userID));
		if (newSeconds > 59) {
			newMinutes++;
			newSeconds = newSeconds % 60;
		}

		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Days", newDays);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Hours", newHours);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Minutes", newMinutes);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Seconds", newSeconds);
		plugin.fileManager.getConfig("PlayerData.yml").save();

		playerTime.remove(userID);
	}

	public void toggleStaffChat(Player p) {
		if (isInStaffChat(p)) {
			this.staffChat.remove(p.getName());
			p.sendMessage(CFMessages.EnteredStaffChat);
		} else {
			this.staffChat.add(p.getName());
			p.sendMessage(CFMessages.EnteredStaffChat);
		}
	}
	
	public void setStaffMode(Player p) {
		if (!isInStaffMode(p)) {
			this.staffMode.add(p.getName());
		}
	}
	
	public void removeStaffMode(Player p) {
		if (isInStaffMode(p)) {
			this.staffMode.remove(p.getName());
		}
	}

	public void forceRemoveStaffChat(Player p) {
		if (isInStaffChat(p)) {
			this.staffChat.remove(p.getName());
		}
	}

	public void forceRemovePlayTime() {
		if (!this.playerTime.isEmpty()) {
			for (Entry<UUID, Long> entry : playerTime.entrySet()) {

				UUID userID = entry.getKey();

				this.endPlayTimeProcess(userID);

				Player p = Bukkit.getPlayer(userID);
				this.forceRemoveStaffChat(p);
			}
		}
	}
	
	/*
	 * Report Part
	 */
	public boolean reportExists(String reportid) {
		return plugin.fileManager.getConfig("ReportData.yml").get().contains(reportid.toString());
	}
	public void initiateReport(UUID authorid, UUID suspectid, String reason) {
		Integer newReportID = this.randomNumber(1000, 9999);
		plugin.fileManager.getConfig("ReportData.yml").get().set(newReportID + ".Author", authorid.toString());
		plugin.fileManager.getConfig("ReportData.yml").get().set(newReportID + ".Suspect", suspectid.toString());
		plugin.fileManager.getConfig("ReportData.yml").get().set(newReportID + ".Reason", removeLastChar(reason));
		plugin.fileManager.getConfig("ReportData.yml").get().set(newReportID + ".Date", datum.format(Calendar.getInstance().getTime()));
		plugin.fileManager.getConfig("ReportData.yml").save();
		Bukkit.getPlayer(authorid).sendMessage(CFMessages.ReportedPlayer(newReportID.toString()));
	}
	public void removeReport(String reportid) {
		plugin.fileManager.getConfig("ReportData.yml").get().set(reportid, null);
		plugin.fileManager.getConfig("ReportData.yml").save();
	}
	public void sendReportList(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "Reports:");
		if (plugin.fileManager.getConfig("ReportData.yml").get().getValues(false).keySet().isEmpty()) {
			sender.sendMessage(CFMessages.NoReportsFound);
		}
		for (String id : plugin.fileManager.getConfig("ReportData.yml").get().getValues(false).keySet()) {
			sender.sendMessage(ChatColor.GRAY + " - " + id);
		}
	}
	public void sendReportInfo(CommandSender sender, String reportID) {
		OfflinePlayer author = Bukkit.getOfflinePlayer(UUID.fromString(plugin.fileManager.getConfig("ReportData.yml").get().getString(reportID + ".Author")));
		OfflinePlayer suspect = Bukkit.getOfflinePlayer(UUID.fromString(plugin.fileManager.getConfig("ReportData.yml").get().getString(reportID + ".Author")));
		// TODO: Add a message Stringlist with replacing values for this
		sender.sendMessage(ChatColor.RED + "Author: " + ChatColor.GRAY + author.getName());
		sender.sendMessage(ChatColor.RED + "Suspect: " + ChatColor.GRAY + suspect.getName());
		sender.sendMessage(ChatColor.RED + "Reason: " + ChatColor.GRAY + plugin.fileManager.getConfig("ReportData.yml").get().getString(reportID + ".Reason"));
		sender.sendMessage(ChatColor.RED + "Date: " + ChatColor.GRAY + plugin.fileManager.getConfig("ReportData.yml").get().getString(reportID + ".Date"));
	}

	/*
	 * Other functions - Transmitters etc.
	 */
	public String argsToString(String[] arguments) { // Capital because its a normally working Java Function
		String str = "";
		for (int i = 0; i != arguments.length; i++) {
			str = str + arguments[i] + " ";
		}
		return str;
	}

	public List<String> colorList(List<String> input) {
		List<String> result = new ArrayList<>();
		for (String string : input) {
			result.add(ChatColor.translateAlternateColorCodes('&', string));
		}
		return result;
	}
	
	private Integer randomNumber(int max, int min) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	private String removeLastChar(String str) {
	    return str.substring(0, str.length() - 1);
	}

}
