package com.optivat.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PollsCommand implements CommandExecutor {
    Polls main;
    public PollsCommand(Polls main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(!main.playerPage.containsKey(player)) {
                main.playerPage.put(player, 1);
            }
            GUIs.updateChangelogInventory(player, main.playerPage.get(player));
        }
        return false;
    }

    public static void setItem(ItemStack stack, String displayName, List<String> lore, int position, Inventory inv) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        if(!lore.isEmpty()) {
            meta.setLore(lore);
        }
        stack.setItemMeta(meta);
        inv.setItem(position, stack);
    }
    public static void setItem(ItemStack stack, String displayName, int position, Inventory inv) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        stack.setItemMeta(meta);
        inv.setItem(position, stack);
    }
}
