package nl.jerskisnow.planetcraftssg.tasks;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import nl.jerskisnow.planetcraftssg.Main;

public class AutoBroadcaster extends BukkitRunnable {
	
	private Main plugin = Main.getPlugin(Main.class);

	@Override
	public void run() {
		List<String> messages = plugin.fileManager.getConfig("AutoBroadcaster.yml").get().getStringList("Messages");
		Random r = new Random();
		int i = r.nextInt(messages.size());
		String msg = ChatColor.translateAlternateColorCodes('&', plugin.fileManager.getConfig("AutoBroadcaster.yml").get().getString("Prefix") + " " + messages.get(i));
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(msg);
		}
		
	}

}
