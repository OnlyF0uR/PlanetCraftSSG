package nl.jerskisnow.planetcraftssg.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import nl.jerskisnow.planetcraftssg.Main;

public class DataManager {

	private HashMap<UUID, Long> playerTime = new HashMap<>();

	private ArrayList<String> staffChat = new ArrayList<>();

	Main plugin;

	public DataManager(Main mainClass) {
		plugin = mainClass;
	}

	public void loadFiles() {
		plugin.fileManager.getConfig("Config.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("Messages.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("PlayerData.yml").copyDefaults(true).save();
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
		 * UUID: Coins: 0 Level: 1 Credits: 0 Time: Days: 0 Hours: 0 Minutes: 0 Seconds:
		 * 0
		 */
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Coins",
				plugin.fileManager.getConfig("Config.yml").get().getDouble("StartersOptions.Coins"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Level",
				plugin.fileManager.getConfig("Config.yml").get().getInt("StartersOptions.Level"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Country",
				plugin.fileManager.getConfig("Config.yml").get().getString("StartersOptions.Country"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Credits",
				plugin.fileManager.getConfig("Config.yml").get().getDouble("StartersOptions.Credits"));
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time", 0); // TODO: There is a change that
																						// this can be removed
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Days", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Hours", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Minutes", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").get().set(userID + ".Time.Seconds", 0L);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	/*
	 * Getters for the PlayerData file
	 */
	public Double getCoins(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getDouble(userID + ".Coins");
	}

	public Integer getLevel(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Level");
	}

	public String getCountry(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getString(userID + ".Country");
	}

	public Double getCredits(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getDouble(userID + ".Credits");
	}

	private int getPlayedDays(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Days");
	}

	private int getPlayedHours(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Hours");
	}

	private int getPlayedMinutes(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Minutes");
	}

	private int getPlayedSeconds(UUID userID) {
		return plugin.fileManager.getConfig("PlayerData.yml").get().getInt(userID + ".Time.Seconds");
	}

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
		if (this.staffChat.contains(p.getName())) {
			return true;
		}
		return false;
	}

	public boolean isAdmin(Player p) {
		if (p.hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Admin"))) {
			return true;
		}
		return false;
	}

	public boolean isStaff(Player p) {
		if (p.hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Staff"))) {
			return true;
		}
		return false;
	}

	public boolean isBuilder(Player p) {
		if (p.hasPermission(plugin.fileManager.getConfig("Config.yml").get().getString("Permissions.Builder"))) {
			return true;
		}
		return false;
	}

	public com.sk89q.worldedit.Vector getPlayerVector(Location loc) {
		return new com.sk89q.worldedit.Vector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	/*
	 * Setters for the PlayerData file
	 */
	public void setCoins(OfflinePlayer p, Double newCoins) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Job", newCoins);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void setLevel(OfflinePlayer p, Integer newLevel) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Age", newLevel);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void addCredits(OfflinePlayer p, Double credits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Tokens",
				this.getCredits(p.getUniqueId()) + credits);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void removeCredits(OfflinePlayer p, Double credits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Tokens",
				this.getCredits(p.getUniqueId()) - credits);
		plugin.fileManager.getConfig("PlayerData.yml").save();
	}

	public void setCredits(OfflinePlayer p, Double newCredits) {
		plugin.fileManager.getConfig("PlayerData.yml").get().set(p.getUniqueId() + ".Tokens", newCredits);
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
	 * Other functions - Transmitters etc.
	 */
	public String ArgsToString(String[] arguments) { // Capital because its a normally working Java Function
		String str = "";
		for (int i = 0; i != arguments.length; i++) {
			str = str + arguments[i] + " ";
		}
		return str;
	}

	public List<String> ColorList(List<String> input) {
		List<String> result = new ArrayList<>();
		for (String string : input) {
			result.add(ChatColor.translateAlternateColorCodes('&', string));
		}
		return result;
	}

}
