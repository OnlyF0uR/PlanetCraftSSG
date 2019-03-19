package nl.jerskisnow.planetcraftssg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.jerskisnow.planetcraftssg.commands.ChatColor;
import nl.jerskisnow.planetcraftssg.commands.Country;
import nl.jerskisnow.planetcraftssg.commands.Credits;
import nl.jerskisnow.planetcraftssg.commands.PlayTime;
import nl.jerskisnow.planetcraftssg.commands.Report;
import nl.jerskisnow.planetcraftssg.commands.StaffChat;
import nl.jerskisnow.planetcraftssg.commands.StaffMode;
import nl.jerskisnow.planetcraftssg.listeners.AntiPlayerReload;
import nl.jerskisnow.planetcraftssg.listeners.BlockBreak;
import nl.jerskisnow.planetcraftssg.listeners.BlockPlace;
import nl.jerskisnow.planetcraftssg.listeners.PlayerChat;
import nl.jerskisnow.planetcraftssg.listeners.PlayerJoin;
import nl.jerskisnow.planetcraftssg.listeners.PlayerMovement;
import nl.jerskisnow.planetcraftssg.listeners.PlayerQuit;
import nl.jerskisnow.planetcraftssg.metrics.Metrics;
import nl.jerskisnow.planetcraftssg.tasks.AutoBroadcaster;
import nl.jerskisnow.planetcraftssg.tasks.LevelTimeChecker;
import nl.jerskisnow.planetcraftssg.tasks.TimeChanger;
import nl.jerskisnow.planetcraftssg.tasks.UpdateScoreboardCoins;
import nl.jerskisnow.planetcraftssg.utils.CustomRecipes;
import nl.jerskisnow.planetcraftssg.utils.DataManager;
import nl.jerskisnow.planetcraftssg.utils.external.FileManager;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class Main extends JavaPlugin {

	public FileManager fileManager = new FileManager(this);

	public DataManager dataManager = new DataManager(this);
	
	private CustomRecipes customRecipes = new CustomRecipes(this);

	public void onEnable() {
		// Check if the server uses the required version, if not disable
		if (!isRequiredVersion()) {
			System.out.println("Plugin has been disabled! Please use 1.13 or higher!");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		
		// Check if the economy setup was successful, if not disable
		if (!Vault.setupEconomy()) {
			System.out.println("Plugin has been disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
		// Load in the custom recipes
		this.customRecipes.loadRecipes();
		
		// Load in the files
		this.dataManager.loadFiles();
		
		// Register Commands & Listeners
		this.loadCommands();
		this.loadListeners();
		
		// Register the Metrics
		new Metrics(this);
		
		// Register the tasks
		this.loadTasks();
	}
	
	public void onDisable() {
		this.dataManager.forceRemovePlayTime();
	}

	private void loadCommands() {
		this.getCommand("time").setExecutor(new PlayTime(this));
		this.getCommand("staffchat").setExecutor(new StaffChat(this));
		this.getCommand("credits").setExecutor(new Credits(this));
		this.getCommand("country").setExecutor(new Country(this));
		this.getCommand("report").setExecutor(new Report(this));
		this.getCommand("chatcolor").setExecutor(new ChatColor(this));
		this.getCommand("staffmode").setExecutor(new StaffMode(this));
	}

	private void loadListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		this.getServer().getPluginManager().registerEvents(new AntiPlayerReload(), this);
		this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		this.getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMovement(), this);
	}
	
	@SuppressWarnings("deprecation")
	private void loadTasks() {
		// Check if realtime is enabled in the configuration file, if yes register task
		if (this.fileManager.getConfig("Config.yml").get().getBoolean("Realtime")) {
			// register the Realtime task
			this.getServer().getScheduler().runTaskTimer(this, new TimeChanger(), 0, 200);
		}
		// Register the task that handles the level up, using the player time in hours
		this.getServer().getScheduler().runTaskTimer(this, new LevelTimeChecker(), 0, 6000);
		// Register the task that handles the scoreboard update for the amount of coins
		this.getServer().getScheduler().runTaskTimer(this, new UpdateScoreboardCoins(), 0, 600);
		
		// Check if AutoBroadcaster is enabled, if yes register task
		if (this.fileManager.getConfig("AutoBroadcaster.yml").get().getBoolean("Enabled")) {
			this.getServer().getScheduler().runTaskTimer(this, new AutoBroadcaster(), 0, this.fileManager.getConfig("AutoBroadcaster.yml").get().getInt("Time_In_Seconds") * 20);
		}
	}

	private boolean isRequiredVersion() {
		String nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		return !nmsver.startsWith("v1_7_") && !nmsver.startsWith("v1_8_") && !nmsver.startsWith("v1_9_")
				&& !nmsver.startsWith("v1_10_") && !nmsver.startsWith("v1_11_") && !nmsver.startsWith("v1_12_");
	}

}
