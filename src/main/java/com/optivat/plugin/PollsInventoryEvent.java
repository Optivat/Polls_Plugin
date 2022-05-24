package com.optivat.plugin;

import org.bukkit.event.Listener;

public class PollsInventoryEvent implements Listener {
    private Polls main;
    public PollsInventoryEvent(Polls main) {
        this.main = main;
    }
}
