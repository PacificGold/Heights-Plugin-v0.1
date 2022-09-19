package com.gmail.thepacificgold.heights;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.Float.parseFloat;

public class HeightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 2) {
                Location currentPos = p.getLocation();
                World currentWorld = currentPos.getWorld();
                double init = parseFloat(args[0]);
                double target = parseFloat(args[1]);
                int isNeg = 0;

                if(init >= target){
                    double temp = init;
                    init = target;
                    target = temp;
                    isNeg = 1;
                }

                double height = target - init;
                height = height * 0.3048;
                double rounded = Math.floor(height);
                double calc = height-rounded;
                Location ret;

                if (calc >= 0.25 && calc <= 0.75) { //half block logic
                    if(isNeg == 1) {
                        double out = Math.ceil(height) + 1;
                        ret = p.getLocation().subtract(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at -" + ChatColor.GOLD + height + ChatColor.RED + " Feet.");
                    } else {
                        double out = Math.ceil(height) - 1;
                        ret = p.getLocation().add(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at " + ChatColor.GOLD + height + ChatColor.RED + " Feet.");
                    }
                    Block placed = currentWorld.getBlockAt(ret);
                    placed.setType(Material.PURPUR_SLAB);
                    p.teleport(ret);
                } else if(calc >= 0.75 && calc <= 1.00){ // FULL BLOCK > 0.75
                    if(isNeg == 1){
                        double out = Math.ceil(height) + 1;
                        ret = p.getLocation().subtract(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at -" + ChatColor.GOLD + height + ChatColor.RED + " Feet.");
                    } else {
                        double out = Math.ceil(height) - 1;
                        ret = p.getLocation().add(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at " + ChatColor.GOLD + height + ChatColor.RED + " Feet.");
                    }
                    Block placed = currentWorld.getBlockAt(ret);
                    placed.setType(Material.PURPUR_BLOCK);
                    p.teleport(ret); // END NEW LOGIC
                } else { // FULL BLOCK < 0.25
                    if(isNeg == 1) {
                        double out = Math.floor(height) + 1;
                        ret = p.getLocation().subtract(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at -" + ChatColor.GOLD + height + ChatColor.RED  + " Feet.");
                    } else {
                        double out = Math.floor(height) - 1;
                        ret = p.getLocation().add(0, out, 0);
                        p.sendMessage(ChatColor.RED + "Height Placed at " + ChatColor.GOLD + height + ChatColor.RED + " Feet.");
                    }
                    Block placed = currentWorld.getBlockAt(ret);
                    placed.setType(Material.PURPUR_BLOCK);
                    p.teleport(ret);
                }
                return true;
            } else {
                p.sendMessage(ChatColor.RED + "Invalid Arguments: use" + ChatColor.GOLD + "/getheight " + ChatColor.RED + " initial target.");
                return false;
            }
        }
        return false;
    }
}