package org.smack17.EvokerStaff.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.smack17.EvokerStaff.Main;

public class GiveStaff implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String arg[]) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used in game.");
        }
        if (cmd.getName().equalsIgnoreCase("givestaff")) {
            Player s = (Player) sender;
            if(s.hasPermission("EvokerStaff.give")) {
                //For giving item to the command sender
                if (arg.length == 0) {
                    Player p = (Player) sender;
                    //Gives the item from Main.class to the sender if their inventory isn't full
                    if (p.getInventory().firstEmpty() == -1) {
                        p.sendMessage(ChatColor.RED + "Your inventory is full! Dropping item on the ground...");
                        Location l = p.getLocation();
                        l.setY(l.getY() + 1);
                        p.getWorld().dropItem(l, Main.item());
                    } else {
                        p.getInventory().addItem(Main.item());
                    }
                }
                //For giving item to another player
                if (arg.length == 1) {
                    Player p = Bukkit.getPlayerExact(arg[0]);
                    if (p == null) {
                        s.sendMessage(ChatColor.RED + "That player is not online!");
                    } else {
                        if (p.getInventory().firstEmpty() == -1) {
                            s.sendMessage(ChatColor.RED + "The player's inventory is full! Dropping item on ground near them...");
                            Location l = p.getLocation();
                            l.setY(l.getY() + 1);
                            p.getWorld().dropItem(l, Main.item());
                        } else {
                            p.getInventory().addItem(Main.item());
                        }
                    }
                }
                //For spawning item at a specific location
                if (arg.length == 3) {
                    double x = Double.parseDouble(arg[0]);
                    double y = Double.parseDouble(arg[1]);
                    double z = Double.parseDouble(arg[2]);
                    Location l = new Location(s.getWorld(), x, y + 1, z);
                    s.getWorld().dropItem(l, Main.item());
                }

                //If too many arguments given
                if (arg.length > 3 || arg.length == 2) {
                    s.sendMessage(ChatColor.RED + "Incorrect command usage!");
                }
            }
        }
        return true;
    }
}
