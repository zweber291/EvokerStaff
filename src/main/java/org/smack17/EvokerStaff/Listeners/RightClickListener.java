package org.smack17.EvokerStaff.Listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BlockIterator;
import org.smack17.EvokerStaff.Main;

public class RightClickListener implements Listener {
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            BlockIterator iter = new BlockIterator(p, 10);
            Block lastBlock = iter.next();
            while(iter.hasNext()) {
                lastBlock = iter.next();
                if(lastBlock.getType().isSolid()) {
                    break;
                }
            }
            Location l = lastBlock.getLocation();
            l.setY(l.getY() + 1.0D);
            l.setX(l.getX() + 0.5D);
            l.setZ(l.getZ() + 0.5D);
            try {
                if(p.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals(Main.item().getItemMeta().getDisplayName())) {
                    Damageable meta = (Damageable) p.getEquipment().getItemInMainHand().getItemMeta();
                    meta.setDamage(meta.getDamage() + 156);
                    if(meta.getDamage() >= 1560) {
                        p.getInventory().clear(p.getInventory().getHeldItemSlot());
                        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                        p.spawnParticle(Particle.CRIT_MAGIC, p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ(), 20);
                    }
                    p.getEquipment().getItemInMainHand().setItemMeta((ItemMeta) meta);
                    p.getWorld().spawnEntity(l, EntityType.EVOKER_FANGS);
                    e.setCancelled(true);
                }
            }
            catch(NullPointerException error) {
                error.printStackTrace();
            }
        }
    }
}
