package nl.jerskisnow.planetcraftssg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.jerskisnow.planetcraftssg.commands.Country;
import nl.jerskisnow.planetcraftssg.commands.Credits;
import nl.jerskisnow.planetcraftssg.commands.PlayTime;
import nl.jerskisnow.planetcraftssg.commands.StaffChat;
import nl.jerskisnow.planetcraftssg.listeners.AntiPlayerReload;
import nl.jerskisnow.planetcraftssg.listeners.PlayerChat;
import nl.jerskisnow.planetcraftssg.listeners.PlayerJoin;
import nl.jerskisnow.planetcraftssg.listeners.PlayerQuit;
import nl.jerskisnow.planetcraftssg.utils.DataManager;
import nl.jerskisnow.planetcraftssg.utils.FileManager;

public class Main extends JavaPlugin {

	public FileManager fileManager = new FileManager(this);

	public DataManager dataManager = new DataManager(this);

	public void onEnable() {
		if (!isRequiredVersion()) {
			System.out.println("Plugin has been disabled! Please use 1.13 or higher!");
			this.getServer().getPluginManager().disablePlugin(this);
		}

		this.dataManager.loadFiles();

		this.loadCommands();
		this.loadListeners();
	}

	public void onDisable() {
		dataManager.forceRemovePlayTime();
	}

	private void loadCommands() {
		this.getCommand("playtime").setExecutor(new PlayTime(this));
		this.getCommand("staffchat").setExecutor(new StaffChat(this));
		this.getCommand("credits").setExecutor(new Credits(this));
		this.getCommand("country").setExecutor(new Country(this));
	}

	private void loadListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		this.getServer().getPluginManager().registerEvents(new AntiPlayerReload(), this);
	}

	private boolean isRequiredVersion() {
		String nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		return !nmsver.startsWith("v1_7_") && !nmsver.startsWith("v1_8_") && !nmsver.startsWith("v1_9_")
				&& !nmsver.startsWith("v1_10_") && !nmsver.startsWith("v1_11_") && !nmsver.startsWith("v1_12_");
	}

}
