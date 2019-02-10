package nl.jerskisnow.planetcraftssg.utils;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapedRecipe;

import nl.jerskisnow.planetcraftssg.Main;

public class CustomRecipes {

	Main plugin;

	public CustomRecipes(Main mainClass) {
		plugin = mainClass;
	}
	
	private ArrayList<Material> woodChoices = new ArrayList<>();
	private ArrayList<Material> logChoices = new ArrayList<>();

	public void loadRecipes() {
		// Remove the right vanilla recipes
		this.removeRecipes();
		
		// Load in the MaterialCoices Arrays
		this.loadChoices();
		
		// Load the cutom recipes
		this.loadBoats();
		this.loadTools();
		this.loadWeapons();
		this.loadGear();
		this.loadOres();
	}
	
	private void loadBoats() {
		/*
		 * OAK BOAT
		 */
		ItemStack oakBoat = new ItemStack(Material.OAK_BOAT);
		NamespacedKey oakBoatKey = new NamespacedKey(plugin, "oakboat_key");
		ShapedRecipe oakBoatRecipe = new ShapedRecipe(oakBoatKey, oakBoat);
		
		oakBoatRecipe.shape("LLL", "SLS", "CLC");
		oakBoatRecipe.setIngredient('L', Material.OAK_LOG);
		oakBoatRecipe.setIngredient('S', Material.STICK);
		oakBoatRecipe.setIngredient('C', Material.COBBLESTONE);
		oakBoatRecipe.setGroup("Boats");

		Bukkit.addRecipe(oakBoatRecipe);
		/*
		 * BIRCH BOAT
		 */
		ItemStack birchBoat = new ItemStack(Material.BIRCH_BOAT);
		NamespacedKey birchBoatKey = new NamespacedKey(plugin, "birchboat_key");
		ShapedRecipe birchBoatRecipe = new ShapedRecipe(birchBoatKey, birchBoat);

		birchBoatRecipe.shape("LLL", "SLS", "CLC");
		birchBoatRecipe.setIngredient('L', Material.BIRCH_LOG);
		birchBoatRecipe.setIngredient('S', Material.STICK);
		birchBoatRecipe.setIngredient('C', Material.COBBLESTONE);
		birchBoatRecipe.setGroup("Boats");	

		Bukkit.addRecipe(birchBoatRecipe);
		/*
		 * SPRUCE BOAT
		 */
		ShapedRecipe ironBoat = new ShapedRecipe(
				new NamespacedKey(plugin, "ironboat_key"),
				new ItemStack(pcMaterial.IRON_BOAT.getMaterial()));
		ironBoat.shape("LLL", "SLS", "CLC");
		ironBoat.setIngredient('L', Material.IRON_INGOT); // TODO: Add the right recipe
		ironBoat.setIngredient('S', Material.STICK);
		ironBoat.setIngredient('C', Material.COBBLESTONE);
		ironBoat.setGroup("Boats");	

		Bukkit.addRecipe(ironBoat);
		/*
		 * JUNGLE BOAT
		 */
		ShapedRecipe jungleBoat = new ShapedRecipe(
				new NamespacedKey(plugin, "jungleboat_key"),
				new ItemStack(Material.JUNGLE_BOAT));
		jungleBoat.shape("LLL", "SLS", "CLC");
		jungleBoat.setIngredient('L', Material.JUNGLE_LOG);
		jungleBoat.setIngredient('S', Material.STICK);
		jungleBoat.setIngredient('C', Material.COBBLESTONE);
		jungleBoat.setGroup("Boats");	

		Bukkit.addRecipe(jungleBoat);
		/*
		 * DARK OAK BOAT
		 */
		ShapedRecipe darkoakBoat = new ShapedRecipe(
				new NamespacedKey(plugin, "darkoakboat_key"),
				new ItemStack(Material.DARK_OAK_BOAT));
		darkoakBoat.shape("LLL", "SLS", "CLC");
		darkoakBoat.setIngredient('L', Material.DARK_OAK_LOG);
		darkoakBoat.setIngredient('S', Material.STICK);
		darkoakBoat.setIngredient('C', Material.COBBLESTONE);
		darkoakBoat.setGroup("Boats");

		Bukkit.addRecipe(darkoakBoat);
		/*
		 * ACACIA BOAT
		 */
		ShapedRecipe acaciaBoat = new ShapedRecipe(
				new NamespacedKey(plugin, "acaciaboat_key"),
				new ItemStack(Material.ACACIA_BOAT));
		acaciaBoat.shape("LLL", "SLS", "CLC");
		acaciaBoat.setIngredient('L', Material.ACACIA_LOG);
		acaciaBoat.setIngredient('S', Material.STICK);
		acaciaBoat.setIngredient('C', Material.COBBLESTONE);
		acaciaBoat.setGroup("Boats");
	}
	
	private void loadTools() {
		/*
		 * STEEL PICKAXE
		 */
		ShapedRecipe steelPickaxeRecipe= new ShapedRecipe(
				new NamespacedKey(plugin, "steelpickaxe_key"),
				new ItemStack(Material.DIAMOND_PICKAXE));
		steelPickaxeRecipe.shape("GNI", "NLN", " L ");
		steelPickaxeRecipe.setIngredient('G', pcMaterial.TITANIUM_INGOT.getMaterial());
		steelPickaxeRecipe.setIngredient('N', pcMaterial.STEEL_INGOT.getMaterial());
		steelPickaxeRecipe.setIngredient('I', Material.IRON_INGOT);
		steelPickaxeRecipe.setIngredient('L', this.getLogChoices());
		Bukkit.addRecipe(steelPickaxeRecipe);
		/*
		 * IRON PICKAXE
		 */
		ShapedRecipe ironPickaxeRecipe = new ShapedRecipe(
				new NamespacedKey(plugin, "ironpickaxe1_key"),
				new ItemStack(Material.IRON_PICKAXE));
		ironPickaxeRecipe.shape("INI", "ILI", " L ");
		ironPickaxeRecipe.setIngredient('I', Material.IRON_INGOT);
		ironPickaxeRecipe.setIngredient('N', pcMaterial.STEEL_INGOT.getMaterial());
		ironPickaxeRecipe.setIngredient('L', this.getLogChoices());
		Bukkit.addRecipe(ironPickaxeRecipe);
		/*
		 * FISHING ROD
		 */
		ShapedRecipe fishingRodRecipe = new ShapedRecipe(
				new NamespacedKey(plugin, "fishingrod_key"),
				new ItemStack(Material.FISHING_ROD));
		fishingRodRecipe.shape("WSS", "W F", "W T");
		fishingRodRecipe.setIngredient('W', this.getWoodChoices());
		fishingRodRecipe.setIngredient('S', Material.STRING);
		fishingRodRecipe.setIngredient('F', Material.FEATHER);
		fishingRodRecipe.setIngredient('T', pcMaterial.TIN_INGOT.getMaterial());
		Bukkit.addRecipe(fishingRodRecipe);
	}
	
	private void loadWeapons() {
		/*
		 * STEEL SWORD
		 */
		ShapedRecipe steelSword = new ShapedRecipe(
				new NamespacedKey(plugin, "steelsword_key"),
				new ItemStack(pcMaterial.STEEL_SWORD.getMaterial()));
		steelSword.shape("IFI", "TQT", " W ");
		steelSword.setIngredient('I', Material.IRON_INGOT);
		steelSword.setIngredient('F', Material.FLINT);
		steelSword.setIngredient('T', pcMaterial.TITANIUM_INGOT.getMaterial());
		steelSword.setIngredient('Q', pcMaterial.STEEL_BLOCK.getMaterial());
		steelSword.setIngredient('W', this.getWoodChoices());
		Bukkit.addRecipe(steelSword);
	}
	
	private void loadGear() {
		/*
		 * STEEL CHESTPLATE
		 */
		ShapedRecipe steelChestPlate = new ShapedRecipe(
				new NamespacedKey(plugin, "steelchestplate_key"),
				new ItemStack(Material.DIAMOND_CHESTPLATE));
		steelChestPlate.shape("VVV", "ILI", "QSQ");
		steelChestPlate.setIngredient('V', Material.LEATHER);
		steelChestPlate.setIngredient('I', Material.IRON_INGOT);
		steelChestPlate.setIngredient('L', pcMaterial.STEEL_BLOCK.getMaterial());
		steelChestPlate.setIngredient('Q', pcMaterial.TITANIUM_INGOT.getMaterial());
		steelChestPlate.setIngredient('S', Material.STRING);
		Bukkit.addRecipe(steelChestPlate);
	}
	
	private void loadOres() {
		/*
		 * STEEL INGOT
		 */
		ShapedRecipe ironNuggetRecipe = new ShapedRecipe(
				new NamespacedKey(plugin, "ironnugget_key"),
				new ItemStack(pcMaterial.STEEL_INGOT.getMaterial()));
		ironNuggetRecipe.shape("MBG", "III", "   ");
		ironNuggetRecipe.setIngredient('M', Material.MAGMA_CREAM);
		ironNuggetRecipe.setIngredient('B', Material.BLAZE_ROD);
		ironNuggetRecipe.setIngredient('G', pcMaterial.NICKEL_INGOT.getMaterial());
		ironNuggetRecipe.setIngredient('I', Material.IRON_INGOT);
		Bukkit.addRecipe(ironNuggetRecipe);
	}
	
	private void removeRecipes() {
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		Recipe recipe;
		do {
			recipe = recipes.next();
			if (recipe != null) {
				switch(recipe.getResult().getType()) {
				case OAK_BOAT:
				case BIRCH_BOAT:
				case SPRUCE_BOAT:
				case JUNGLE_BOAT:
				case DARK_OAK_BOAT:
				case ACACIA_BOAT:
				case IRON_NUGGET:
				case DIAMOND_PICKAXE:
				case DIAMOND_CHESTPLATE:
				case IRON_PICKAXE:
				case FISHING_ROD:
				case DIAMOND_SWORD:
					recipes.remove();
					break;
				default:
					break;
				}
			}
		} while (recipes.hasNext());
	}
	
	private MaterialChoice getWoodChoices() {
		return new MaterialChoice(woodChoices);
	}
	
	private MaterialChoice getLogChoices() {
		return new MaterialChoice(logChoices);
	}
	
	private void loadChoices() {
		// Wood Choices
		woodChoices.add(Material.OAK_WOOD);
		woodChoices.add(Material.BIRCH_WOOD);
		woodChoices.add(Material.SPRUCE_WOOD);
		woodChoices.add(Material.JUNGLE_WOOD);
		woodChoices.add(Material.DARK_OAK_WOOD);
		woodChoices.add(Material.ACACIA_WOOD);
		
		// Log Choices
		logChoices.add(Material.OAK_LOG);
		logChoices.add(Material.BIRCH_LOG);
		logChoices.add(Material.SPRUCE_LOG);
		logChoices.add(Material.JUNGLE_LOG);
		logChoices.add(Material.DARK_OAK_LOG);
		logChoices.add(Material.ACACIA_LOG);
	}

}
