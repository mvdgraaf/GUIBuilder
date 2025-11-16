package me.Codex.GUIBuilder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiListener implements Listener {

    private static final Map<Integer, GuiItem> items = new HashMap<>();

    public static void register(Inventory inventory, int slot, GuiItem item) {
        items.put(inventory.hashCode() + slot, item);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        int key = e.getInventory().hashCode() + e.getSlot();

        if (items.containsKey(key)) {
            e.setCancelled(true);
            items.get(key).action().accept(e);
        }
    }
}
