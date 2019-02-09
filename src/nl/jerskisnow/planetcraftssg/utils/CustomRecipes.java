package nl.jerskisnow.planetcraftssg.utils;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import nl.jerskisnow.planetcraftssg.Main;

public class CustomRecipes {

	Main plugin;

	public CustomRecipes(Main mainClass) {
		plugin = mainClass;
	}

	public void loadRecipes() {
		removeRecipes();
		loadBoats();
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

		Bukkit.addRecipe(oakBoatRecipe);
		
		/*
		 * BIRCH BOAT
		 */
		ItemStack birchBoat = new ItemStack(Material.OAK_BOAT);
		NamespacedKey birchBoatKey = new NamespacedKey(plugin, "birchboat_key");
		ShapedRecipe birchBoatRecipe = new ShapedRecipe(birchBoatKey, birchBoat);

		birchBoatRecipe.shape("LLL", "SLS", "CLC");
		birchBoatRecipe.setIngredient('L', Material.BIRCH_LOG);
		birchBoatRecipe.setIngredient('S', Material.STICK);
		birchBoatRecipe.setIngredient('C', Material.COBBLESTONE);

		Bukkit.addRecipe(birchBoatRecipe);
		/*
		 * SPRUCE BOAT
		 */
		
		/*
		 * JUNGLE BOAT
		 */
		
		/*
		 * DARK OAK BOAT
		 */
		
		/*
		 * ACACIA BOAT
		 */
	}
	
	private void removeRecipes() {
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		Recipe recipe;
		do {
			recipe = recipes.next();
			if (recipe != null) {
				switch(recipe.getResult().getType()) {
				case OAK_BOAT:
					recipes.remove();
					break;
				case BIRCH_BOAT:
					recipes.remove();
					break;
				case SPRUCE_BOAT:
					recipes.remove();
					break;
				case JUNGLE_BOAT:
					recipes.remove();
					break;
				case DARK_OAK_BOAT:
					recipes.remove();
					break;
				case ACACIA_BOAT:
					recipes.remove();
					break;
				default:
					break;
				}
			}
		} while (recipes.hasNext());
	}

}
