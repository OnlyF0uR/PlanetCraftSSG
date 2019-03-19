package nl.jerskisnow.planetcraftssg.trading;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import nl.jerskisnow.planetcraftssg.utils.CFMessages;
import nl.jerskisnow.planetcraftssg.utils.external.Vault;

public class TradeMenuListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().contains("Trade")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.OAK_LOG) {
				/*
				 * START MIDDLEWARE PART OF DIVISION
				 */
				if (e.getClick().isLeftClick()) { // Buy
					if (e.getClick().isShiftClick() && player.hasPermission("planetcraftssg.bulktrade")) { // 64 Items
						if (tradeMenuItems.OAK_LOG.getBuyPrice() * 64 < Vault.econ.getBalance(player)) { // Not enough coins
							player.sendMessage(CFMessages.NotEnoughCoins);
						} else { // Enough coins
							Vault.econ.withdrawPlayer(player, tradeMenuItems.OAK_LOG.getBuyPrice() * 64);
							player.getInventory().addItem(new ItemStack(Material.OAK_LOG, 64));
						}
					} else { // 1 Item
						if (tradeMenuItems.OAK_LOG.getBuyPrice() < Vault.econ.getBalance(player)) { // Not enough coins
							player.sendMessage(CFMessages.NotEnoughCoins);
						} else { // Enough coins
							Vault.econ.withdrawPlayer(player, tradeMenuItems.OAK_LOG.buyPrice);
							player.getInventory().addItem(new ItemStack(Material.OAK_LOG, 1));
						}
					}
				} else { // Sell
					if (e.getClick().isShiftClick() && player.hasPermission("planetcraftssg.bulktrade")) { // 64 Items
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.OAK_LOG), 64)) { // Not enough items
							player.sendMessage(CFMessages.NotEnoughItems);
						} else { // Enough items
							player.getInventory().remove(new ItemStack(Material.OAK_LOG, 64));
							Vault.econ.depositPlayer(player, tradeMenuItems.OAK_LOG.getSellPrice() * 64);
						}
					} else { // 1 Item
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.OAK_LOG), 1)) { // Not enough items
							player.sendMessage(CFMessages.NotEnoughItems);
						} else { // Enough items
							player.getInventory().remove(new ItemStack(Material.OAK_LOG));
							Vault.econ.depositPlayer(player, tradeMenuItems.OAK_LOG.getSellPrice());
						}
					}
				}
				/*
				 * END MIDDLEWARE PART OF DIVISION
				 */
			} else if (e.getCurrentItem().getType() == Material.SPRUCE_LOG) {
				
			} else if (e.getCurrentItem().getType() == Material.BIRCH_LOG) {
				
			} else if (e.getCurrentItem().getType() == Material.JUNGLE_LOG) {
				
			} else if (e.getCurrentItem().getType() == Material.ACACIA_LOG) {
				
			} else if (e.getCurrentItem().getType() == Material.DARK_OAK_LOG) {
				
			}
			player.closeInventory();
		}
		
		
		
	}

}
