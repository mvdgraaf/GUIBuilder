package me.Codex.GUIBuilder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CodexGui extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        getLogger().info("CodexGui enabled!");
    }

    @Override
    public void onDisable() {
        // Nothing needed
    }
}
