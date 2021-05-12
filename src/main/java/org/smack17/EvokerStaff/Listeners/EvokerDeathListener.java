package org.smack17.EvokerStaff.Listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Evoker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.smack17.EvokerStaff.Main;

public class EvokerDeathListener implements Listener {
    FileConfiguration config;
    public EvokerDeathListener(Main instance) {
        config = instance.getConfig();
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        Location l = e.getEntity().getLocation();
        l.setY(l.getY() + 1.0D);
        if(config.getBoolean("evokerDrop") && e.getEntity() instanceof Evoker) {
            e.getEntity().getWorld().dropItem(l, Main.item());
        }
    }
}
