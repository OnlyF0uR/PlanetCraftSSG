package nl.jerskisnow.planetcraftssg.trading;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TradeMenu {
	
	private static Inventory tradeInventory;
	
	/*
	 * 0  1  2  3  4  5  6  7  8
	 * 9  10 11 12 13 14 15 16 17
	 * 18 19 20 21 22 23 24 25 26
	 * 27 28 29 30 31 32 33 34 35
	 * 36 37 38 39 40 41 42 43 44
	 * 45 46 47 48 49 50 51 52 53
	 */
	
	public static void openTradeMenu(Player p) {
		tradeInventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Trade");
		
		/*
		 * Normal Logs
		 */
		tradeInventory.setItem(0, tradeMenuItems.OAK_LOG.getItemStack());
		tradeInventory.setItem(1, tradeMenuItems.SPRUCE_LOG.getItemStack());
		tradeInventory.setItem(2, tradeMenuItems.BIRCH_LOG.getItemStack());
		tradeInventory.setItem(3, tradeMenuItems.JUNGLE_LOG.getItemStack());
		tradeInventory.setItem(4, tradeMenuItems.ACACIA_LOG.getItemStack());
		tradeInventory.setItem(5, tradeMenuItems.DARK_OAK_LOG.getItemStack());
		/*
		 * Stripped Logs
		 */
		tradeInventory.setItem(6, tradeMenuItems.STRIPPED_OAK_LOG.getItemStack());
		tradeInventory.setItem(7, tradeMenuItems.STRIPPED_SPRUCE_LOG.getItemStack());
		tradeInventory.setItem(8, tradeMenuItems.STRIPPED_BIRCH_LOG.getItemStack());
		tradeInventory.setItem(9, tradeMenuItems.STRIPPED_JUNGLE_LOG.getItemStack());
		tradeInventory.setItem(10, tradeMenuItems.STRIPPED_ACACIA_LOG.getItemStack());
		tradeInventory.setItem(11, tradeMenuItems.STRIPPED_DARK_OAK_LOG.getItemStack());
		/*
		 * Normal Planks
		 */
		tradeInventory.setItem(12, tradeMenuItems.OAK_PLANKS.getItemStack());
		tradeInventory.setItem(13, tradeMenuItems.SPRUCE_PLANKS.getItemStack());
		tradeInventory.setItem(14, tradeMenuItems.BIRCH_PLANKS.getItemStack());
		tradeInventory.setItem(15, tradeMenuItems.JUNGLE_PLANKS.getItemStack());
		tradeInventory.setItem(16, tradeMenuItems.ACACIA_PLANKS.getItemStack());
		tradeInventory.setItem(17, tradeMenuItems.DARK_OAK_PLANKS.getItemStack());
		/*
		 * Stones
		 */
		tradeInventory.setItem(12, tradeMenuItems.COBBLESTONE.getItemStack());
		tradeInventory.setItem(13, tradeMenuItems.MOSSY_COBBLESTONE.getItemStack());
		tradeInventory.setItem(14, tradeMenuItems.STONE.getItemStack());
		tradeInventory.setItem(15, tradeMenuItems.STONE_BRICKS.getItemStack());
		tradeInventory.setItem(16, tradeMenuItems.MOSSY_STONE_BRICKS.getItemStack());
		tradeInventory.setItem(17, tradeMenuItems.CRACKED_STONE_BRICKS.getItemStack());
		tradeInventory.setItem(18, tradeMenuItems.CHISELED_STONE_BRICKS.getItemStack());
		tradeInventory.setItem(19, tradeMenuItems.GRANITE.getItemStack());
		tradeInventory.setItem(20, tradeMenuItems.DIORITE.getItemStack());
		tradeInventory.setItem(21, tradeMenuItems.ANDESITE.getItemStack());
		/*
		 * Dirt, Sand etc.
		 */
		tradeInventory.setItem(22, tradeMenuItems.DIRT.getItemStack());
		tradeInventory.setItem(23, tradeMenuItems.COARSE_DIRT.getItemStack());
		tradeInventory.setItem(24, tradeMenuItems.GRASS_BLOCK.getItemStack());
		tradeInventory.setItem(25, tradeMenuItems.PODZOL.getItemStack());
		tradeInventory.setItem(26, tradeMenuItems.SAND.getItemStack());
		tradeInventory.setItem(27, tradeMenuItems.RED_SAND.getItemStack());
		tradeInventory.setItem(28, tradeMenuItems.SANDSTONE.getItemStack());
		tradeInventory.setItem(29, tradeMenuItems.RED_SANDSTONE.getItemStack());
		
		
		// Open the inventory for 'p'
		p.openInventory(tradeInventory);
	}

}
