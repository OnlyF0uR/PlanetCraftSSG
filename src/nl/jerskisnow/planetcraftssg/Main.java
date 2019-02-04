import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import nl.jerskisnow.planetcraftssg.banking.Vault;
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
		
		if (!Vault.setupEconomy() ) {
			System.out.println("Plugin has been disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
		this.dataManager.loadFiles();

		this.loadCommands();
		this.loadListeners();
		
		this.loadTasks();
	}
	
	public void onDisable() {
		dataManager.forceRemovePlayTime();
	}
	
	private void loadCommands() {
		this.getCommand("playtime").setExecutor(new PlayTime(this));
		this.getCommand("staffchat").setExecutor(new StaffChat(this));
	}
	
	private void loadListeners() {
		/*
		 * Normal Listeners
		 */
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		/*
		 * Banking & ATM Listeners
		 */
	}
	
	@SuppressWarnings("deprecation")
	private void loadTasks() {
		
	}
	
	private boolean isRequiredVersion() {
		String nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		return !nmsver.startsWith("v1_7_") && !nmsver.startsWith("v1_8_") && !nmsver.startsWith("v1_9_")
				&& !nmsver.startsWith("v1_10_") && !nmsver.startsWith("v1_11_") && !nmsver.startsWith("v1_12_");
	}

}
