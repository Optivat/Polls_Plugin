package com.optivat.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PollCreateCommand implements CommandExecutor {
    Polls main;
    public PollCreateCommand(Polls polls) {
        main = polls;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            main.creatingPoll.put(player, 1);
            player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Please hold out the item you want to be used to show the poll.");
            player.sendMessage(ChatColor.AQUA + "You can type \"cancel \" at any time to stop creating a poll.");
            player.sendMessage(ChatColor.YELLOW + "Please type the name of the poll (Not the question)");
        }
        return false;
    }
}
