package nl.jerskisnow.planetcraftssg.api.data;

import org.bukkit.OfflinePlayer;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class PlayerData {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	OfflinePlayer p;
	
	public PlayerData(OfflinePlayer player) {
		p = player;
	}
	
	public Double getCoins() {
		return Vault.econ.getBalance(p);
	}
	
	public Integer getLevel() {
		return plugin.dataManager.getLevel(p.getUniqueId());
	}
	
	public String getCountry() {
		return plugin.dataManager.getCountry(p.getUniqueId());
	}
	
	public Double getCredits() {
		return plugin.dataManager.getCredits(p.getUniqueId());
	}
	
	@Deprecated
	public String getPlayedTime() {
		return plugin.dataManager.getPlayedTime(p.getUniqueId());
	}
	
	public Integer getPlayedDays() {
		return plugin.dataManager.getPlayedDays(p.getUniqueId());
	}
	
	public Integer getPlayedHours() {
		return plugin.dataManager.getPlayedHours(p.getUniqueId());
	}
	
	public Integer getPlayedMinutes() {
		return plugin.dataManager.getPlayedDays(p.getUniqueId());
	}
	
	public Integer getPlayedSeconds() {
		return plugin.dataManager.getPlayedSeconds(p.getUniqueId());
	}
	
	public boolean isAdmin() {
		return plugin.dataManager.isAdmin(p);
	}
	
	public boolean isStaff() {
		return plugin.dataManager.isStaff(p);
	}
	
	public boolean isBuilder() {
		return plugin.dataManager.isBuilder(p);
	}
	
	public boolean isRegistered() {
		return plugin.dataManager.isRegisteredPlayer(p.getUniqueId());
	}

}
