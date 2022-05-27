package com.optivat.plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PollsInventoryEvent implements Listener {
    private Polls main;
    public PollsInventoryEvent(Polls main) {
        this.main = main;
    }


    //Poll Creation
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        if(main.creatingPoll.containsValue(e.getPlayer())) {
            Player player = e.getPlayer();
            if(e.getPlayer().getInventory().getItemInHand() != null) {
                e.setCancelled(true);
                int stage = main.creatingPoll.get(player);
                ItemStack item = e.getPlayer().getInventory().getItemInHand();
                ItemMeta itemMeta = item.getItemMeta();
                if(e.getMessage().trim().equalsIgnoreCase("cancel")) {
                    main.creatingPoll.remove(e.getPlayer());
                    player.sendMessage(ChatColor.BLUE + "You have stopped creating a poll!");
                    return;
                }
                if(stage == 1) {
                    main.creatingPoll.replace(player, 2);
                    itemMeta.setDisplayName(ChatColor.YELLOW + e.getMessage().trim());
                    player.sendMessage(ChatColor.YELLOW + "Now choose the question for your poll.");
                    return;
                }
                if(stage == 2) {

                }
            } else {
                player.sendMessage(ChatColor.RED + "You don't have an item in your hand!");
            }
        }
    }
}
