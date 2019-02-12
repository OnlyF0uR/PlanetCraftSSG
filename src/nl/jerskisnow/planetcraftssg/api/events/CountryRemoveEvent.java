package nl.jerskisnow.planetcraftssg.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CountryRemoveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    
    Player p;
    private String cName;

    public CountryRemoveEvent(Player player, String countryName) {
    	p = player;
    	cName = countryName;
    }
    
    public Player getPlayer() {
    	return p;
    }

    public String getCountryName() {
        return cName;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
