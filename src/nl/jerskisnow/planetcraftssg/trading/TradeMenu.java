package nl.jerskisnow.planetcraftssg.trading;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import nl.jerskisnow.planetcraftssg.utils.external.ItemBuilder;

public class TradeMenu {
	
	private Inventory tradeInventory;
	
	/*
	 * 0  1  2  3  4  5  6  7  8
	 * 9  10 11 12 13 14 15 16 17
	 * 18 19 20 21 22 23 24 25 26
	 * 27 28 29 30 31 32 33 34 35
	 * 36 37 38 39 40 41 42 43 44
	 * 45 46 47 48 49 50 51 52 53
	 */
	
	public void openTradeMenu(Player p) {
		tradeInventory = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Trade");
		
		/*
		 * Normal Logs
		 */
		ItemStack oakLog = new ItemBuilder(Material.OAK_LOG)
				.setName("Oak Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		ItemStack spruceLog = new ItemBuilder(Material.SPRUCE_LOG)
				.setName("Spruce Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		ItemStack birchLog = new ItemBuilder(Material.BIRCH_LOG)
				.setName("Birch Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		ItemStack jungleLog = new ItemBuilder(Material.JUNGLE_LOG)
				.setName("Jungle Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		ItemStack acaciaLog = new ItemBuilder(Material.ACACIA_LOG)
				.setName("Acacia Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		ItemStack darkOakLog = new ItemBuilder(Material.DARK_OAK_LOG)
				.setName("Dark Oak Log")
				.setLore("Sellprice : 2.0\nBuyprice : 4.0").toItemStack();
		/*
		 * Stripped Logs
		 */
		ItemStack strippedOakLog = new ItemBuilder(Material.STRIPPED_OAK_LOG)
				.setName("Stripped Oak Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		ItemStack strippedSpruceLog = new ItemBuilder(Material.STRIPPED_SPRUCE_LOG)
				.setName("Stripped Spruce Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		ItemStack strippedBirchLog = new ItemBuilder(Material.STRIPPED_BIRCH_LOG)
				.setName("Stripped Birch Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		ItemStack strippedJungleLog = new ItemBuilder(Material.STRIPPED_JUNGLE_LOG)
				.setName("Stripped Jungle Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		ItemStack strippedAcaciaLog = new ItemBuilder(Material.STRIPPED_ACACIA_LOG)
				.setName("Stripped Acacia Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		ItemStack strippedDarkOakLog = new ItemBuilder(Material.STRIPPED_DARK_OAK_LOG)
				.setName("Stripped Dark Oak Log")
				.setLore("Sellprice : 1.0\nBuyprice : 2.0").toItemStack();
		/*
		 * Wood Planks
		 */
		ItemStack oakPlanks = new ItemBuilder(Material.OAK_PLANKS)
				.setName("Oak Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		ItemStack sprucePlanks = new ItemBuilder(Material.SPRUCE_PLANKS)
				.setName("Spruce Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		ItemStack birchPlanks = new ItemBuilder(Material.BIRCH_PLANKS)
				.setName("Birch Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		ItemStack junglePlanks = new ItemBuilder(Material.JUNGLE_PLANKS)
				.setName("Jungle Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		ItemStack acaciaPlanks = new ItemBuilder(Material.ACACIA_PLANKS)
				.setName("Acacia Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		ItemStack darkOakPlanks = new ItemBuilder(Material.DARK_OAK_PLANKS)
				.setName("Dark Oak Planks")
				.setLore("Sellprice : 0.50\nBuyprice : 1.0").toItemStack();
		
		/*
		 * Normal Logs
		 */
		tradeInventory.setItem(0, oakLog);
		tradeInventory.setItem(1, spruceLog);
		tradeInventory.setItem(2, birchLog);
		tradeInventory.setItem(3, jungleLog);
		tradeInventory.setItem(4, acaciaLog);
		tradeInventory.setItem(5, darkOakLog);
		/*
		 * Stripped Logs
		 */
		tradeInventory.setItem(6, strippedOakLog);
		tradeInventory.setItem(7, strippedSpruceLog);
		tradeInventory.setItem(8, strippedBirchLog);
		tradeInventory.setItem(9, strippedJungleLog);
		tradeInventory.setItem(10, strippedAcaciaLog);
		tradeInventory.setItem(11, strippedDarkOakLog);
		/*
		 * Wood Planks
		 */
		tradeInventory.setItem(12, oakPlanks);
		tradeInventory.setItem(13, sprucePlanks);
		tradeInventory.setItem(14, birchPlanks);
		tradeInventory.setItem(15, junglePlanks);
		tradeInventory.setItem(16, acaciaPlanks);
		tradeInventory.setItem(17, darkOakPlanks);
		
		// Open the inventory for 'p'
		p.openInventory(tradeInventory);
	}

}
