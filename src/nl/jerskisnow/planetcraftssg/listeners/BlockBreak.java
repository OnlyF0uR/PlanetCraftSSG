package nl.jerskisnow.planetcraftssg.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import nl.jerskisnow.planetcraftssg.Main;
import nl.jerskisnow.planetcraftssg.api.events.ActiveCooldownEvent;
import nl.jerskisnow.planetcraftssg.utils.CFMessages;

public class BlockBreak implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	private ArrayList<String> cooldown = new ArrayList<>();
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {		
		if (!cooldown.contains(e.getPlayer().getName())) {
			if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
				cooldown.add(e.getPlayer().getName());
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						cooldown.remove(e.getPlayer().getName());
					}
				}, (long) (plugin.fileManager.getConfig("Config.yml").get().getLong("BlockBreakCooldown") * 20));	
			}
			if (this.isShovelBlock(e.getBlock().getType())) {
				if (!this.isShovelTool(e.getPlayer().getInventory().getItemInMainHand().getType())) {
					if (plugin.fileManager.getConfig("Config").get().getBoolean("CancelBlockBreakWithWrongTool")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(CFMessages.WrongTool);
						return;
					}
					e.setDropItems(false);
				}
			} else if (this.isAxeBlock(e.getBlock().getType())) {
				if (!this.isAxeTool(e.getPlayer().getInventory().getItemInMainHand().getType())) {
					if (plugin.fileManager.getConfig("Config").get().getBoolean("CancelBlockBreakWithWrongTool")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(CFMessages.WrongTool);
						return;
					}
					e.setDropItems(false);
				}
			} else if (this.isShearBlock(e.getBlock().getType())) {
				if (!this.isShearTool(e.getPlayer().getInventory().getItemInMainHand().getType())) {
					if (plugin.fileManager.getConfig("Config").get().getBoolean("CancelBlockBreakWithWrongTool")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(CFMessages.WrongTool);
						return;
					}
					e.setDropItems(false);
				}
			} else if (this.isHoeBlock(e.getBlock().getType())) {
				if (!this.isHoeTool(e.getPlayer().getInventory().getItemInMainHand().getType())) {
					if (plugin.fileManager.getConfig("Config").get().getBoolean("CancelBlockBreakWithWrongTool")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(CFMessages.WrongTool);
						return;
					}
					e.setDropItems(false);
				}
			}
		} else {
			e.getPlayer().sendMessage(CFMessages.ActiveCooldown);
			e.setCancelled(true);
			Bukkit.getServer().getPluginManager().callEvent(new ActiveCooldownEvent(e.getPlayer(), "break"));
		}
	}
	
	private boolean isShovelBlock(Material blockMaterial) {
		boolean boolVar = false;
		switch(blockMaterial) {
		case GRASS_BLOCK:
		case DIRT:
		case COARSE_DIRT:
		case SAND:
		case RED_SAND:
		case GRAVEL:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isAxeBlock(Material blockMaterial) {
		boolean boolVar = false;
		switch(blockMaterial) {
		case OAK_WOOD:
		case BIRCH_WOOD:
		case SPRUCE_WOOD:
		case JUNGLE_WOOD:
		case DARK_OAK_WOOD:
		case ACACIA_WOOD:
		case OAK_LOG:
		case BIRCH_LOG:
		case SPRUCE_LOG:
		case JUNGLE_LOG:
		case DARK_OAK_LOG:
		case ACACIA_LOG:
		case OAK_PLANKS:
		case BIRCH_PLANKS:
		case SPRUCE_PLANKS:
		case JUNGLE_PLANKS:
		case DARK_OAK_PLANKS:
		case ACACIA_PLANKS:
		case STRIPPED_OAK_WOOD:
		case STRIPPED_BIRCH_WOOD:
		case STRIPPED_SPRUCE_WOOD:
		case STRIPPED_JUNGLE_WOOD:
		case STRIPPED_DARK_OAK_WOOD:
		case STRIPPED_ACACIA_WOOD:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isShearBlock(Material blockMaterial) {
		boolean boolVar = false;
		switch(blockMaterial) {
		case OAK_LEAVES:
		case BIRCH_LEAVES:
		case SPRUCE_LEAVES:
		case JUNGLE_LEAVES:
		case DARK_OAK_LEAVES:
		case ACACIA_LEAVES:
		case BLACK_WOOL:
		case RED_WOOL:
		case GREEN_WOOL:
		case BROWN_WOOL:
		case BLUE_WOOL:
		case PURPLE_WOOL:
		case CYAN_WOOL:
		case LIGHT_GRAY_WOOL:
		case GRAY_WOOL:
		case PINK_WOOL:
		case LIME_WOOL:
		case YELLOW_WOOL:
		case LIGHT_BLUE_WOOL:
		case MAGENTA_WOOL:
		case ORANGE_WOOL:
		case WHITE_WOOL:
		case VINE:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isHoeBlock(Material blockMaterial) {
		boolean boolVar = false;
		switch(blockMaterial) {
		case LILY_PAD:
		case OAK_SAPLING:
		case BIRCH_SAPLING:
		case SPRUCE_SAPLING:
		case JUNGLE_SAPLING:
		case DARK_OAK_SAPLING:
		case ACACIA_SAPLING:
		case GRASS:
		case TALL_GRASS:
		case SEAGRASS:
		case TALL_SEAGRASS:
		case DANDELION:
		case POPPY:
		case BLUE_ORCHID:
		case ALLIUM:
		case AZURE_BLUET:
		case RED_TULIP:
		case ORANGE_TULIP:
		case WHITE_TULIP:
		case PINK_TULIP:
		case OXEYE_DAISY:
		case SUNFLOWER:
		case LILAC:
		case ROSE_BUSH:
		case PEONY:
		case CARROTS:
		case POTATOES:
		case BEETROOTS:
		case WHEAT:
		case MELON:
		case MELON_STEM:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isShovelTool(Material toolMaterial) {
		boolean boolVar = false;
		switch(toolMaterial) {
		case DIAMOND_SHOVEL:
		case GOLDEN_SHOVEL:
		case IRON_SHOVEL:
		case STONE_SHOVEL:
		case WOODEN_SHOVEL:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isAxeTool(Material toolMaterial) {
		boolean boolVar = false;
		switch(toolMaterial) {
		case DIAMOND_AXE:
		case GOLDEN_AXE:
		case IRON_AXE:
		case STONE_AXE:
		case WOODEN_AXE:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}
	
	private boolean isShearTool(Material toolMaterial) {
		return toolMaterial == Material.SHEARS;
	}
	
	private boolean isHoeTool(Material toolMaterial) {
		boolean boolVar = false;
		switch(toolMaterial) {
		case DIAMOND_HOE:
		case GOLDEN_HOE:
		case IRON_HOE:
		case STONE_HOE:
		case WOODEN_HOE:
			boolVar = true;
			break;
		default:
			break;
		}
		return boolVar;
	}

}
