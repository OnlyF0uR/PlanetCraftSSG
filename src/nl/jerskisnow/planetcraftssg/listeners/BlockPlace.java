package nl.jerskisnow.planetcraftssg.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.api.events.ActiveCooldownEvent;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class BlockPlace implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	private ArrayList<String> cooldown = new ArrayList<>();

	@EventHandler
	public void onPlance(BlockPlaceEvent e) {
		if (!cooldown.contains(e.getPlayer().getName())) {
			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				cooldown.add(e.getPlayer().getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(e.getPlayer().getName());
					}
				}, (long) (plugin.fileManager.getConfig("Config.yml").get().getLong("BlockPlaceCooldown") * 20));	
			}
		} else {
			e.getPlayer().sendMessage(CFMessages.ActiveCooldown);
			e.setCancelled(true);
			Bukkit.getServer().getPluginManager().callEvent(new ActiveCooldownEvent(e.getPlayer(), "place"));
		}
	}

}
