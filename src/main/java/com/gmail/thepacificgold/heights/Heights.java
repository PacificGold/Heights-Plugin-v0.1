package com.gmail.thepacificgold.heights;

import org.bukkit.plugin.java.JavaPlugin;

public final class Heights extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("getheight").setExecutor(new HeightCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
