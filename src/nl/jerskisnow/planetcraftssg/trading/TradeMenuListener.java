package nl.jerskisnow.planetcraftssg.trading;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import nl.jerskisnow.planetcraftssg.utils.CFMessages;
import nl.jerskisnow.planetcraftssg.utils.ScoreboardManager;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class TradeMenuListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().contains("Trade")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			e.setCancelled(true);
			switch(e.getCurrentItem().getType()) {
			case OAK_LOG:
				this.middleWare(tradeMenuItems.OAK_LOG, Material.OAK_LOG, e.getClick(), player);
				break;
			case SPRUCE_LOG:
				this.middleWare(tradeMenuItems.SPRUCE_LOG, e.getCurrentItem().getType(), e.getClick(), player);
				break;
			case BIRCH_LOG:
				this.middleWare(tradeMenuItems.BIRCH_LOG, e.getCurrentItem().getType(), e.getClick(), player);
				break;
			case JUNGLE_LOG:
				this.middleWare(tradeMenuItems.JUNGLE_LOG, e.getCurrentItem().getType(), e.getClick(), player);
				break;
			case ACACIA_LOG:
				this.middleWare(tradeMenuItems.ACACIA_LOG, e.getCurrentItem().getType(), e.getClick(), player);
				break;
			case DARK_OAK_LOG:
				this.middleWare(tradeMenuItems.DARK_OAK_LOG, e.getCurrentItem().getType(), e.getClick(), player);
				break;
			default:
				break;
			}
		}
	}
	
	
	private void middleWare(tradeMenuItems item, Material mat, ClickType click, Player player) {
		/*
		 * START MIDDLEWARE PART OF DIVISION
		 */
		if (click.isLeftClick()) { // Buy
			if (click.isShiftClick() && player.hasPermission("planetcraftssg.bulktrade")) { // 64 Items
				if (item.getBuyPrice() * 64 > Vault.econ.getBalance(player)) { // Not enough coins
					player.sendMessage(CFMessages.NotEnoughCoins);
				} else { // Enough coins
					Vault.econ.withdrawPlayer(player, item.getBuyPrice() * 64);
					player.getInventory().addItem(new ItemStack(mat, 64));
					ScoreboardManager.initiateCoins(player);
				}
			} else { // 1 Item
				if (item.getBuyPrice() > Vault.econ.getBalance(player)) { // Not enough coins
					player.sendMessage(CFMessages.NotEnoughCoins);
				} else { // Enough coins
					Vault.econ.withdrawPlayer(player, item.buyPrice);
					player.getInventory().addItem(new ItemStack(mat, 1));
					ScoreboardManager.initiateCoins(player);
				}
			}
		} else { // Sell
			if (click.isShiftClick() && player.hasPermission("planetcraftssg.bulktrade")) { // 64 Items
				if (!player.getInventory().containsAtLeast(new ItemStack(mat), 64)) { // Not enough items
					player.sendMessage(CFMessages.NotEnoughItems);
				} else { // Enough items
					player.getInventory().removeItem(new ItemStack(mat, 64));
					Vault.econ.depositPlayer(player, item.getSellPrice() * 64);
					ScoreboardManager.initiateCoins(player);
				}
			} else { // 1 Item
				if (!player.getInventory().containsAtLeast(new ItemStack(mat), 1)) { // Not enough items
					player.sendMessage(CFMessages.NotEnoughItems);
				} else { // Enough items
					player.getInventory().removeItem(new ItemStack(mat));
					Vault.econ.depositPlayer(player, item.getSellPrice());
					ScoreboardManager.initiateCoins(player);
				}
			}
		}
		/*
		 * END MIDDLEWARE PART OF DIVISION
		 */
	}
	

}
