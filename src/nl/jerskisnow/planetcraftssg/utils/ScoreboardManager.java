package nl.jerskisnow.planetcraftssg.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class ScoreboardManager {

	private static Main plugin = Main.getPlugin(Main.class);

	// Defining Main Scoreboard Setup
	private static Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
	@SuppressWarnings("deprecation")
	private static Objective o = board.registerNewObjective("PlanetCraft", "");

	// Team Declarations
	private static Team coinsTeam = board.registerNewTeam("coins");
	private static Team levelTeam = board.registerNewTeam("level");
	private static Team creditsTeam = board.registerNewTeam("credits");

	public static void startScoreboard(Player p) {
		o.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				plugin.fileManager.getConfig("Config.yml").get().getString("Scoreboard.Title")));
		o.setDisplaySlot(DisplaySlot.SIDEBAR);

		o.getScore(ChatColor.translateAlternateColorCodes('&', "&3Coins:")).setScore(9);

		coinsTeam.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&a"));
		coinsTeam.setPrefix("");
		initiateCoins(p);
		o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&a")).setScore(8);

		o.getScore("§1").setScore(7);

		o.getScore(ChatColor.translateAlternateColorCodes('&', "&3Level:")).setScore(6);

		levelTeam.addEntry(ChatColor.translateAlternateColorCodes('&', "&7"));
		levelTeam.setPrefix("");
		initiateLevel(p);
		o.getScore(ChatColor.translateAlternateColorCodes('&', "&7")).setScore(5);

		o.getScore("§2").setScore(4);

		o.getScore(ChatColor.translateAlternateColorCodes('&', "&3Credits:")).setScore(3);

		creditsTeam.addEntry(ChatColor.translateAlternateColorCodes('&', "&a"));
		creditsTeam.setPrefix("");
		initiateCredits(p);
		o.getScore(ChatColor.translateAlternateColorCodes('&', "&a")).setScore(2);
		
		o.getScore("§3").setScore(1);
		
		o.getScore(ChatColor.translateAlternateColorCodes('&', plugin.fileManager.getConfig("Config.yml").get().getString("Scoreboard.Footer"))).setScore(0);

		p.setScoreboard(board);
	}

	public static void initiateCoins(Player p) {
		coinsTeam.setSuffix(String.valueOf(Vault.econ.getBalance(p)));
	}

	public static void initiateLevel(Player p) {
		levelTeam.setSuffix(plugin.dataManager.getLevel(p.getUniqueId()).toString());
	}

	public static void initiateCredits(Player p) {
		creditsTeam.setSuffix(plugin.dataManager.getCredits(p.getUniqueId()).toString());
	}
}
