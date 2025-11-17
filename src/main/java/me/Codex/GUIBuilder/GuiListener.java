package me.Codex.GUIBuilder;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiListener implements Listener {

    private static final Map<Inventory, GuiBuilder> NormalGui = new HashMap<>();
    private static final Map<Inventory, Map<Integer, GuiItem>> PagedGui = new HashMap<>();

    public static void register(Inventory inventory, GuiBuilder gui) {
        NormalGui.put(inventory, gui);
    }

    public static void registerPaged(Inventory inventory, Map<Integer, GuiItem> items) {
        PagedGui.put(inventory, items);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inventory = e.getInventory();
        int slot = e.getRawSlot();
        Player player = (Player) e.getWhoClicked();

        GuiBuilder normal = NormalGui.get(inventory);
        if (normal != null) {
            e.setCancelled(true);
            GuiItem item = normal.items.get(slot);
            if(item != null && item.action() != null) {
                item.action().accept(e);
            }
            return;
        }

        Map<Integer, GuiItem> paged = PagedGui.get(inventory);
        if (paged != null) {
            if (slot < inventory.getSize()) {
                e.setCancelled(true);
                GuiItem item = paged.get(slot);
                if (item != null && item.action() != null) {
                    item.action().accept(e);
                }
            }
        }
    }
}
