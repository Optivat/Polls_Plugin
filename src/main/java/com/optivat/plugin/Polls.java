package com.optivat.plugin;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class Polls extends JavaPlugin {

    public static File file;
    HashMap<Player, Integer> playerPage = new HashMap<>();
    HashMap<Player, Integer> creatingPoll = new HashMap<>();

    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        GUIs gui = new GUIs(this);
        getCommand("polls").setExecutor(new PollsCommand(this));
        getCommand("createpoll").setExecutor(new PollCreateCommand(this));
        Bukkit.getPluginManager().registerEvents(new PollsInventoryEvent(this), this);
        try {
            getDataFolder().mkdir();
            file = new File(getDataFolder(), "changelogs.json");
            fileCreation();
        } catch (IOException e) {
            System.out.println("Error! Unable to create " + "polls.json");
        }
    }

    private void fileCreation() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            Poll changelog = new Poll(ChatColor.RED + "v0.1", Arrays.asList(ChatColor.RED + "first line!", ChatColor.GOLD.toString() + ChatColor.BOLD + "second line...", ChatColor.STRIKETHROUGH + "amogus"), new ItemStack(Material.BEDROCK, 1), 1);
            Gson gson = new Gson();
            Writer writer = new FileWriter(file, false);
            gson.toJson(changelog, writer);
            writer.flush();
            writer.close();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static boolean version1_18() {
        if(!(Bukkit.getBukkitVersion().contains("1.8") || Bukkit.getVersion().contains("1.7") || Bukkit.getBukkitVersion().contains("1.9") || Bukkit.getBukkitVersion().contains("1.10") || Bukkit.getBukkitVersion().contains("1.11") || Bukkit.getBukkitVersion().contains("1.12"))) {
            return true;
        }
        return false;
    }
}
