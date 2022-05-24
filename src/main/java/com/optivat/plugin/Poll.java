package com.optivat.plugin;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Poll {
    private String displayName;
    private List<String> pollQuestion;
    private ItemStack itemStack;
    private ItemStack itemStack1_18;
    private int pollPage;
    public Poll(String dN, List<String> pQ, ItemStack iS, int pP) {
        displayName = dN;
        pollQuestion = pQ;
        if(Polls.version1_18()) {
            itemStack1_18 = iS;
            itemStack = null;
        } else {
            itemStack = iS;
            itemStack1_18 = null;
        }
        pollPage = pP;
    }
    public String getDisplayName() { return displayName; }
    public List<String> getLore() { return pollQuestion; }
    public ItemStack getItemStack() { return itemStack; }
    public ItemStack getItemStack1_18() { return itemStack1_18; }
    public int getPollPage() { return pollPage; }
}
