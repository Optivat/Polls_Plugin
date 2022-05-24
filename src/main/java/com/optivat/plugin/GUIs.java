package com.optivat.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GUIs {
    private Polls main;
    public GUIs (Polls main) {
        this.main = main;
    }
    public static Inventory createBasicInventory(Player player, String name) {
        Inventory inv = Bukkit.createInventory(player, 36, name);
        player.openInventory(inv);
        for(int x = 0; x <=8; x++) {
            PollsCommand.setItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), ChatColor.BLACK + "", x, inv);
        }
        for(int x = 9; x <=26; x++) {
            PollsCommand.setItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), ChatColor.BLACK + "", x, inv);
            x+=8;
            PollsCommand.setItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), ChatColor.BLACK + "", x, inv);
        }
        for(int x = 27; x <=35; x++) {
            if(!(x == 27 || x == 35)) {
                PollsCommand.setItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15), ChatColor.BLACK + "", x, inv);
            }
        }
        PollsCommand.setItem(new ItemStack(Material.ARROW, 1), ChatColor.RED + "Back", 27, inv);
        PollsCommand.setItem(new ItemStack(Material.ARROW, 1), ChatColor.RED + "Next", 35, inv);
        return inv;
    }
    public static Inventory createBasicInventory_1_18(Player player, String name) {
        Inventory inv = Bukkit.createInventory(player, 54, name);
        player.openInventory(inv);
        for(int x = 0; x <=8; x++) {
            setItem1_18(inv, x);
        }
        for(int x = 9; x <=26; x++) {
            setItem1_18(inv, x);
            x+=8;
            setItem1_18(inv, x);
        }
        for(int x = 27; x <=35; x++) {
            if(!(x == 27 || x == 35)) {
                setItem1_18(inv, x);
            }
        }
        PollsCommand.setItem(new ItemStack(Material.ARROW, 1), ChatColor.RED + "Back", 27, inv);
        PollsCommand.setItem(new ItemStack(Material.ARROW, 1), ChatColor.RED + "Next", 35, inv);
        return inv;
    }

    private static void setItem1_18(Inventory inv, int x) {
        try {
            Class materialClass = Class.forName("org.bukkit.Material");
            Method getMaterial = materialClass.getMethod("valueOf", String.class);
            Material mat = (Material) getMaterial.invoke(null, "BLACK_STAINED_GLASS_PANE");
            PollsCommand.setItem(new ItemStack(mat, 1), ChatColor.BLACK + "", x, inv);

        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Inventory updateChangelogInventory(Player player, int page) {
        Inventory inv;
        if (Polls.version1_18()) {
            inv = createBasicInventory_1_18(player, ChatColor.GOLD.toString() + ChatColor.BOLD + "Changelogs");
        } else {
            inv = createBasicInventory(player, ChatColor.GOLD.toString() + ChatColor.BOLD + "Changelogs");
        }
        try {
            Reader reader = new FileReader(Polls.file);
            Gson gson = new Gson();
            JsonStreamParser p = new JsonStreamParser(reader);
            int position = 10;
            while (p.hasNext()) {
                JsonElement jsonElement = p.next();
                if (jsonElement.isJsonObject()) {
                    Poll changelog = gson.fromJson(jsonElement, Poll.class);
                    if(changelog.getPollPage() == page) {
                        if(Polls.version1_18()) {
                            PollsCommand.setItem(changelog.getItemStack1_18(), changelog.getDisplayName(), changelog.getLore(), position, inv);
                        } else {
                            PollsCommand.setItem(changelog.getItemStack(), changelog.getDisplayName(), changelog.getLore(), position, inv);
                        }
                        if(position == 16 || position == 25) {
                            position+=3;
                        } else {
                            position+=1;
                        }
                    }
                }
                /* handle other JSON data structures */
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inv;
    }
}
