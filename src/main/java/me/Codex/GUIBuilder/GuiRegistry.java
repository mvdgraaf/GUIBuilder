package me.Codex.GUIBuilder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class GuiRegistry {

    private static final Set<String> registeredPlugins = new HashSet<>();
    private static boolean listenerRegistered = false;

    public static void register(Plugin plugin) {
        if (registeredPlugins.contains(plugin.getName())) {
            return;
        }

        if (!listenerRegistered) {
            Bukkit.getPluginManager().registerEvents(new GuiListener(), plugin);
            listenerRegistered = true;
            plugin.getLogger().info("Registered Gui listener");
        }

        plugin.getLogger().info("Plugin registered: " + plugin.getName());
    }
}
