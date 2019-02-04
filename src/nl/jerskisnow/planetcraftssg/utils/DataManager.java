package nl.jerskisnow.planetcraftssg.utils;

import nl.jerskisnow.planetcraftssg.Main;

public class DataManager {
	
	Main plugin;
	
	public DataManager(Main mainClass) {
		plugin = mainClass;
	}

	public void loadFiles() {
		plugin.fileManager.getConfig("Config.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("Messages.yml").copyDefaults(true).save();
		plugin.fileManager.getConfig("PlayerData.yml").copyDefaults(true).save();
	}

}
