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
		 * ItemStacks
		 */
		ItemStack oakLog = new ItemBuilder(Material.OAK_LOG)
				.setName("Oak Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		ItemStack spruceLog = new ItemBuilder(Material.SPRUCE_LOG)
				.setName("Spruce Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		ItemStack birchLog = new ItemBuilder(Material.BIRCH_LOG)
				.setName("Birch Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		ItemStack jungleLog = new ItemBuilder(Material.JUNGLE_LOG)
				.setName("Jungle Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		ItemStack acaciaLog = new ItemBuilder(Material.ACACIA_LOG)
				.setName("Acacia Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		ItemStack darkOakLog = new ItemBuilder(Material.DARK_OAK_LOG)
				.setName("Dark Oak Log")
				.setLore("Sellprice : 2.0\nBuyprice : 0.0").toItemStack();
		
		/*
		 * ItemStack Setters
		 */
		tradeInventory.setItem(0, oakLog);
		tradeInventory.setItem(1, spruceLog);
		tradeInventory.setItem(2, birchLog);
		tradeInventory.setItem(3, jungleLog);
		tradeInventory.setItem(4, acaciaLog);
		tradeInventory.setItem(5, darkOakLog);
		
		// Open the inventory for 'p'
		p.openInventory(tradeInventory);
	}

}