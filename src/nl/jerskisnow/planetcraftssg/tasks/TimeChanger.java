package nl.jerskisnow.planetcraftssg.tasks;

import org.bukkit.scheduler.*;

import java.util.*;
import org.bukkit.*;

public class TimeChanger extends BukkitRunnable {
    
    public void run() {
        final Calendar cal = Calendar.getInstance();
        cal.getTime();
        final int hour = cal.get(11) + 0;
        final int minute = cal.get(12);
        final int minutes = hour * 60 + minute;
        final int seconds = minutes * 60 + cal.get(13);
        final int newtick = seconds * 20 / 72;
        for (World world : Bukkit.getServer().getWorlds()) {
            int realtime = (newtick - 6000) % 24000;
            if (realtime < 0) {
                realtime += 24000;
            }
            world.setFullTime((long)realtime);
        }
    }
}
