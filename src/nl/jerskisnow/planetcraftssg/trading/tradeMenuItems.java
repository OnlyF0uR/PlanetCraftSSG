package nl.jerskisnow.planetcraftssg.trading;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import nl.jerskisnow.planetcraftssg.utils.external.ItemBuilder;

public enum tradeMenuItems {
	
	// Format: MATERIAL_NAME(BUYPRICE, SELLPRICE, DISPLAYNAME)
	
	/*
	 * Normal Logs
	 */
	OAK_LOG(4.0, 2.0, "&fOak Log"),
	SPRUCE_LOG(4.0, 2.0, "&fSpruce Log"),
	BIRCH_LOG(4.0, 2.0, "&fBirch Log"),
	JUNGLE_LOG(4.0, 2.0, "J&fungle Log"),
	ACACIA_LOG(4.0, 2.0, "&fAcacia Log"),
	DARK_OAK_LOG(4.0, 2.0, "&fDark Oak Log"),
	/*
	 * Stripped Logs
	 */
	STRIPPED_OAK_LOG(2.0, 1.0, "&fStripped Oak Log"),
	STRIPPED_SPRUCE_LOG(2.0, 1.0, "&fStripped Spruce Log"),
	STRIPPED_BIRCH_LOG(2.0, 1.0, "&fStripped Birch Log"),
	STRIPPED_JUNGLE_LOG(2.0, 1.0, "&fStripped Jungle Log"),
	STRIPPED_ACACIA_LOG(2.0, 1.0, "&fStripped Acacia Log"),
	STRIPPED_DARK_OAK_LOG(2.0, 1.0, "&fStripped Dark Oak Log"),
	/*
	 * Normal Planks
	 */
	OAK_PLANKS(1.0, 0.50, "&fOak Planks"),
	SPRUCE_PLANKS(1.0, 0.50, "&fSpruce Planks"),
	BIRCH_PLANKS(1.0, 0.50, "&fBirch Planks"),
	JUNGLE_PLANKS(1.0, 0.50, "&fJungle Planks"),
	ACACIA_PLANKS(1.0, 0.50, "&fAcacia Planks"),
	DARK_OAK_PLANKS(1.0, 0.50, "&fDark Oak Planks"),
	/*
	 * Stones
	 */
	COBBLESTONE(0.2, 0.1, "&fCobblestone"),
	MOSSY_COBBLESTONE(4.0, 2.0, "&fMossy Cobblestone"),
	STONE(1.0, 0.5, "&fStone"),
	STONE_BRICKS(1.0, 0.5, "&fStone Bricks"),
	MOSSY_STONE_BRICKS(8.0, 4.0, "&fMossy Stone Bricks"),
	CRACKED_STONE_BRICKS(6.0, 3.0, "&fCracked Stone Bricks"),
	CHISELED_STONE_BRICKS(8.0, 4.0, "&fMossy Stone Bricks"),
	GRANITE(0.4, 0.2, "&fGranite"),
	DIORITE(0.4, 0.2, "&fDiorite"),
	ANDESITE(0.4, 0.2, "&fAndesite"),
	/*
	 * Dirt, Sand etc.
	 */
	DIRT(0.2, 0.1, "&fDirt"),
	COARSE_DIRT(0.4, 0.2, "&fCoarse Dirt"),
	GRASS_BLOCK(1.0, 0.5, "&fGrass"),
	PODZOL(8.0, 4.0, "&fPodzol"),
	SAND(0.4, 0.2, "&fSand"),
	RED_SAND(0.8, 0.4, "&fRed Sand"),
	SANDSTONE(1.0, 0.5, "&fSandstone"),
	RED_SANDSTONE(2.0, 1.0, "&fRed Sandstone");
	
	double buyPrice;
	double sellPrice;
	String displayName;
	
	// Constructor
	tradeMenuItems(double bPrice, double sPrice, String dName){
		this.buyPrice = bPrice;
		this.sellPrice = sPrice;
		this.displayName = dName;
	}
	
	public Double getBuyPrice() {
		return buyPrice;
	}
	
	public Double getSellPrice() {
		return sellPrice;
	}
	
	public ItemStack getItemStack() {
		return new ItemBuilder(Material.matchMaterial(this.toString()))
				.setName(ChatColor.translateAlternateColorCodes('&', this.displayName))
				.setLore("Buyprice : " + buyPrice + "\nSellprice : " + sellPrice)
				.toItemStack();
	}
	
}