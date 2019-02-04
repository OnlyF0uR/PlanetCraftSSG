package nl.jerskisnow.planetcraftssg.utils;

import org.bukkit.World;

import com.sk89q.worldguard.WorldGuard;

public class CountryManager {

	public static boolean countryExists(String countryname, World world) {
		return WorldGuard.getInstance().getPlatform().getRegionContainer()
				.get(WorldGuard.getInstance().getPlatform().getWorldByName(world.getName())).hasRegion(countryname);
	}

	public static enum CountryRole {
		OWNER("Owner"), MEMBER("Member");
		private String rolename;

		private CountryRole(String brand) {
			this.rolename = brand;
		}

		@Override
		public String toString() {
			return rolename;
		}
	}

}
