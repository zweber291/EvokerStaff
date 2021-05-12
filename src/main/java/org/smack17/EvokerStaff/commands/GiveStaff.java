package org.smack17.EvokerStaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.smack17.EvokerStaff.Main;

public class GiveStaff implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String arg[]) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command may only be used in game.");
        }
        if(cmd.getName().equalsIgnoreCase("givestaff"))
        {
            Player p = (Player)sender;
            if(p.hasPermission("EvokerStaff.give"))
            {
                p.getInventory().addItem(Main.item());
            }
        }
        return true;
    }
}
