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
        //Enable message
        logger.info((new StringBuilder(getDescription().getName())).append(" v")
                .append(getDescription().getVersion())
                .append(" by ").append(getDescription().getAuthors())
                .append(" has been enabled!").toString());
        saveDefaultConfig(); //Copies the config in the jar to the plugin's folder

        //Register Events
        getServer().getPluginManager().registerEvents(new EvokerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);

        //Register Commands
        getCommand("givestaff").setExecutor(new GiveStaff());
    }

    public void onDisable() {
        //Disable message
        logger.info((new StringBuilder(getDescription().getName())).append(" has been disabled.").toString());
    }

    //This is the item that players will receive when using the givestaff command or killing an evoker
    public static ItemStack item() {
        ItemStack i = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta m = i.getItemMeta();

        //Set item name
        m.setDisplayName((new StringBuilder()).append(ChatColor.GRAY).append("Evoker Staff").toString());

        //Set item lore
        List lore = new ArrayList();
        lore.add((new StringBuilder()).append(ChatColor.RED).append("Used by the Evoker").toString());
        lore.add((new StringBuilder()).append(ChatColor.RED).append("to summon its fangs.").toString());
        lore.add((new StringBuilder()).append(ChatColor.GOLD).append("Left-click to use.").toString());
        m.setLore(lore);
        i.setItemMeta(m);
        return i; //returns the item for use in other classes
    }
}
