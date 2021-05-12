package org.smack17.EvokerStaff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.smack17.EvokerStaff.Listeners.EvokerDeathListener;
import org.smack17.EvokerStaff.Listeners.RightClickListener;
import org.smack17.EvokerStaff.commands.GiveStaff;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private Logger logger;
    public static Main plugin;
    FileConfiguration config;
    public Main() {
        logger = Bukkit.getLogger();
        config = getConfig();
    }

    public void onEnable() {
        logger.info((new StringBuilder(getDescription().getName())).append(" v")
                .append(getDescription().getVersion())
                .append(" by ").append(getDescription().getAuthors())
                .append(" has been enabled!").toString());
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EvokerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getCommand("givestaff").setExecutor(new GiveStaff());
    }

    public void onDisable() {
        logger.info((new StringBuilder(getDescription().getName())).append(" has been disabled.").toString());
    }

    public static ItemStack item() {
        ItemStack i = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName((new StringBuilder()).append(ChatColor.GRAY).append("Evoker Staff").toString());
        List lore = new ArrayList();
        lore.add((new StringBuilder()).append(ChatColor.RED).append("Used by the Evoker").toString());
        lore.add((new StringBuilder()).append(ChatColor.RED).append("to summon its fangs.").toString());
        lore.add((new StringBuilder()).append(ChatColor.GOLD).append("Left-click to use.").toString());
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }
}
