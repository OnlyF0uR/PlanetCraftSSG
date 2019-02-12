package nl.jerskisnow.planetcraftssg.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActiveCooldownEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    
    Player p;
    private String sName;

    public ActiveCooldownEvent(Player player, String sortName) {
    	p = player;
    	sName = sortName;
    }
    
    public Player getPlayer() {
    	return p;
    }
    
    @Deprecated
    public boolean isBreakAction() {
    	return sName.equals("break");
    }
    
    @Deprecated
    public boolean isPlaceAction() {
    	return sName.equals("place");
    }
    
    public String getSort() {
        return sName;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
