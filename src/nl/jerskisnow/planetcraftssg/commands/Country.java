package nl.jerskisnow.planetcraftssg.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionOperationException;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.RegionGroup;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.api.events.CountryCreateEvent;
import nl.jerskisnow.planetcraftssg.api.events.CountryRemoveEvent;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;
import nl.jerskisnow.planetcraftssg.utils.CountryManager;
import nl.jerskisnow.planetcraftssg.utils.CountryManager.CountryRole;

public class Country implements CommandExecutor {

	Main plugin;

	public Country(Main mainFile) {
		plugin = mainFile;
	}

	private ArrayList<String> memberArray = new ArrayList<>();

	private ArrayList<String> ownerArray = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (plugin.dataManager.isAdmin((Player) sender)) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create")) {

					WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager()
							.getPlugin("WorldEdit");

					World world = worldEdit.getSession((Player) sender).getSelectionWorld();

					Region sel;
					try {
						sel = worldEdit.getSession((Player) sender).getSelection(world);
					} catch (IncompleteRegionException e) {
						sender.sendMessage(CFMessages.NoSelectionFound);
						return true;
					}

					if (CountryManager.countryExists(args[1], ((Player) sender).getWorld())) {
						sender.sendMessage(CFMessages.CountryAlreadyExists);
						return true;
					}

					try {
						// Expand with 100 up
						worldEdit.getSession((Player) sender).getSelection(world).expand(new Vector(0, 100, 0));

						// Expand with 25 down
						worldEdit.getSession((Player) sender).getSelection(world).expand(new Vector(0, -25, 0));
					} catch (RegionOperationException | IncompleteRegionException e) {
						e.printStackTrace();
					}

					ProtectedCuboidRegion region = new ProtectedCuboidRegion(args[1],
							new BlockVector(sel.getMinimumPoint()), new BlockVector(sel.getMaximumPoint()));

					region.setFlag(Flags.INTERACT, StateFlag.State.DENY);
					region.setFlag(Flags.INTERACT.getRegionGroupFlag(), RegionGroup.NON_MEMBERS);

					region.setFlag(Flags.CHEST_ACCESS, StateFlag.State.DENY);
					region.setFlag(Flags.CHEST_ACCESS.getRegionGroupFlag(), RegionGroup.NON_MEMBERS);

					region.setFlag(Flags.USE, StateFlag.State.ALLOW);
					region.setFlag(Flags.USE.getRegionGroupFlag(), RegionGroup.MEMBERS);

					region.setFlag(Flags.PVP, StateFlag.State.DENY);

					RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
					RegionManager regions = container.get(world);
					regions.addRegion(region);

					sender.sendMessage(CFMessages.CountryHasBeenCreated(args[1]));
					Bukkit.getServer().getPluginManager().callEvent(new CountryCreateEvent((Player) sender, args[1]));

				} else if (args[0].equalsIgnoreCase("remove")) {
					if (!CountryManager.countryExists(args[1], ((Player) sender).getWorld())) {
						sender.sendMessage(CFMessages.CountryDoesNotExists);
						return true;
					}

					WorldGuard.getInstance().getPlatform().getRegionContainer().get(WorldGuard.getInstance()
							.getPlatform().getWorldByName(((Player) sender).getWorld().getName()))
							.removeRegion(args[1]);

					sender.sendMessage(CFMessages.CountryHasBeenRemoved(args[1]));
					Bukkit.getServer().getPluginManager().callEvent(new CountryRemoveEvent((Player) sender, args[1]));
				} else if (args[0].equalsIgnoreCase("addmember")) {
					Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

					@SuppressWarnings("deprecation")
					OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);

					for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
							.get(WorldGuard.getInstance().getPlatform()
									.getWorldByName(((Player) sender).getWorld().getName()))
							.getApplicableRegions(playerLocationVector)) {
						r.getMembers().addPlayer(mentionedPlayer.getUniqueId());
						plugin.dataManager.setCountry(mentionedPlayer.getUniqueId(), r.getId());
						sender.sendMessage(CFMessages.AddedPlayerToCountry(mentionedPlayer.getName(), r.getId(),
								CountryRole.MEMBER.toString()));
						return true;
					}
					sender.sendMessage(CFMessages.InvalidCountry);
				} else if (args[0].equalsIgnoreCase("removemember")) {
					Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

					@SuppressWarnings("deprecation")
					OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);

					for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
							.get(WorldGuard.getInstance().getPlatform()
									.getWorldByName(((Player) sender).getWorld().getName()))
							.getApplicableRegions(playerLocationVector)) {
						r.getMembers().removePlayer(mentionedPlayer.getUniqueId());
						plugin.dataManager.setCountry(mentionedPlayer.getUniqueId(), plugin.fileManager.getConfig("Config.yml").get().getString("StartersOptions.Country"));
						sender.sendMessage(CFMessages.RemovedPlayerFromCountry(mentionedPlayer.getName(), r.getId(),
								CountryRole.MEMBER.toString()));
						return true;
					}
					sender.sendMessage(CFMessages.InvalidCountry);
				} else if (args[0].equalsIgnoreCase("addowner")) {
					Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

					@SuppressWarnings("deprecation")
					OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);

					for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
							.get(WorldGuard.getInstance().getPlatform()
									.getWorldByName(((Player) sender).getWorld().getName()))
							.getApplicableRegions(playerLocationVector)) {
						r.getOwners().addPlayer(mentionedPlayer.getUniqueId());
						plugin.dataManager.setCountry(mentionedPlayer.getUniqueId(), r.getId());
						sender.sendMessage(CFMessages.AddedPlayerToCountry(mentionedPlayer.getName(), r.getId(),
								CountryRole.OWNER.toString()));
						return true;
					}
					sender.sendMessage(CFMessages.InvalidCountry);
				} else if (args[0].equalsIgnoreCase("removeowner")) {
					Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

					@SuppressWarnings("deprecation")
					OfflinePlayer mentionedPlayer = Bukkit.getOfflinePlayer(args[1]);

					for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
							.get(WorldGuard.getInstance().getPlatform()
									.getWorldByName(((Player) sender).getWorld().getName()))
							.getApplicableRegions(playerLocationVector)) {
						r.getOwners().removePlayer(mentionedPlayer.getUniqueId());
						plugin.dataManager.setCountry(mentionedPlayer.getUniqueId(), plugin.fileManager.getConfig("Config.yml").get().getString("StartersOptions.Country"));
						sender.sendMessage(CFMessages.RemovedPlayerFromCountry(mentionedPlayer.getName(), r.getId(),
								CountryRole.OWNER.toString()));
						return true;
					}
					sender.sendMessage(CFMessages.InvalidCountry);
				} else {
					this.sendHelp(sender);
				}

			} else if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
				Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

				for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
						.get(WorldGuard.getInstance().getPlatform()
								.getWorldByName(((Player) sender).getWorld().getName()))
						.getApplicableRegions(playerLocationVector)) {
					r.getOwners().removeAll();
					r.getMembers().removeAll();
					sender.sendMessage(CFMessages.ClearedCountry);
					return true;
				}
				sender.sendMessage(CFMessages.InvalidCountry);
			} else if (args.length == 0) {
				this.sendHelp(sender);
			} else if (args[0].equalsIgnoreCase("info")) {
				Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

				for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
						.get(WorldGuard.getInstance().getPlatform()
								.getWorldByName(((Player) sender).getWorld().getName()))
						.getApplicableRegions(playerLocationVector)) {
					// Owners
					Object[] pOwners = r.getOwners().getUniqueIds().toArray();
					for (int i = 0; i < pOwners.length; i++) {
						OfflinePlayer player = Bukkit.getOfflinePlayer((UUID) pOwners[i]);
						ownerArray.add(player.getName());
					}
					String finalOwners = "None";
					if (!ownerArray.isEmpty()) {
						finalOwners = String.join(", ", ownerArray);
					}
					// Members
					Object[] pMembers = r.getMembers().getUniqueIds().toArray();
					for (int i = 0; i < pMembers.length; i++) {
						OfflinePlayer player = Bukkit.getOfflinePlayer((UUID) pMembers[i]);
						memberArray.add(player.getName());
					}
					String finalMembers = "None";
					if (!memberArray.isEmpty()) {
						finalMembers = String.join(", ", memberArray);
					}

					List<String> msg = plugin.dataManager
							.colorList(plugin.fileManager.getConfig("Messages.yml").get().getStringList("PlotInfo"));
					for (String string : msg) {
						sender.sendMessage(string.replaceAll("<Country>", r.getId()).replaceAll("<Owners>", finalOwners)
								.replaceAll("<Members>", finalMembers));
					}
					ownerArray.clear();
					memberArray.clear();
					return true;
				}
				sender.sendMessage(CFMessages.InvalidCountry);
			} else {
				this.sendHelp(sender);
			}
		} else {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.RED + "/country info");
				sender.sendMessage(ChatColor.RED + "/country leave");
				return true;
			}
			if (args[0].equalsIgnoreCase("info")) {
				Vector playerLocationVector = plugin.dataManager.locationToVector(((Player) sender).getLocation());

				for (ProtectedRegion r : WorldGuard.getInstance().getPlatform().getRegionContainer()
						.get(WorldGuard.getInstance().getPlatform()
								.getWorldByName(((Player) sender).getWorld().getName()))
						.getApplicableRegions(playerLocationVector)) {
					// Owners
					Object[] pOwners = r.getOwners().getUniqueIds().toArray();
					for (int i = 0; i < pOwners.length; i++) {
						OfflinePlayer player = Bukkit.getOfflinePlayer((UUID) pOwners[i]);
						ownerArray.add(player.getName());
					}
					String finalOwners = "None";
					if (!ownerArray.isEmpty()) {
						finalOwners = String.join(", ", ownerArray);
					}
					// Members
					Object[] pMembers = r.getMembers().getUniqueIds().toArray();
					for (int i = 0; i < pMembers.length; i++) {
						OfflinePlayer player = Bukkit.getOfflinePlayer((UUID) pMembers[i]);
						memberArray.add(player.getName());
					}
					String finalMembers = "None";
					if (!memberArray.isEmpty()) {
						finalMembers = String.join(", ", memberArray);
					}

					List<String> msg = plugin.dataManager
							.colorList(plugin.fileManager.getConfig("Messages.yml").get().getStringList("PlotInfo"));
					for (String string : msg) {
						sender.sendMessage(string.replaceAll("<Country>", r.getId()).replaceAll("<Owners>", finalOwners)
								.replaceAll("<Members>", finalMembers));
					}
					ownerArray.clear();
					memberArray.clear();
					return true;
				}
				sender.sendMessage(CFMessages.InvalidCountry);
			} else if (args[0].equalsIgnoreCase("leave")) {
				ProtectedRegion r = WorldGuard.getInstance().getPlatform().getRegionContainer()
						.get(WorldGuard.getInstance().getPlatform().getWorldByName(((Player) sender).getWorld().getName()))
						.getRegion(plugin.dataManager.getCountry(((Player) sender).getUniqueId()));
				if (r == null) {
					sender.sendMessage(CFMessages.NotAMemberOfACountry);
					return true;
				}
				r.getMembers().removePlayer(((Player) sender).getUniqueId());
				sender.sendMessage(CFMessages.LeftCountry);
			} else {
				sender.sendMessage(ChatColor.RED + "/country info");
				sender.sendMessage(ChatColor.RED + "/country leave");
			}
		}
		return true;
	}

	private void sendHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "/country create <Name>");
		sender.sendMessage(ChatColor.RED + "/country remove <Name>");
		sender.sendMessage(ChatColor.RED + "/country addmember <Name>");
		sender.sendMessage(ChatColor.RED + "/country removemember <Name>");
		sender.sendMessage(ChatColor.RED + "/country addowner <Name>");
		sender.sendMessage(ChatColor.RED + "/country removeowner <Name>");
		sender.sendMessage(ChatColor.RED + "/country clear");
		sender.sendMessage(ChatColor.RED + "/country info");
	}

}
