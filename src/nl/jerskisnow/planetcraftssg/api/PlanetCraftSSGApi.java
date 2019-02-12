package nl.jerskisnow.planetcraftssg.api;

import org.bukkit.OfflinePlayer;

import nl.jerskisnow.planetcraftssg.api.data.PlayerData;

public class PlanetCraftSSGApi {
	
	public static PlayerData getPlayerData(OfflinePlayer p) {
		if (p instanceof OfflinePlayer) {
			return new PlayerData(p);
		}
		return null;
	}
	
	

}
