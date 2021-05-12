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
        //Gets location that an entity died
        Location l = e.getEntity().getLocation();
        l.setY(l.getY() + 1.0D);
        //If evokerDrop is true in the config and the entity that died is an evoker, drop the staff
        if(config.getBoolean("evokerDrop") && e.getEntity() instanceof Evoker) {
            e.getEntity().getWorld().dropItem(l, Main.item());
        }
    }
}
