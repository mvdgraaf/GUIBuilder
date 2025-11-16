package me.Codex.GUIBuilder;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public record GuiItem(ItemStack item, Consumer<InventoryClickEvent> action) {

    public GuiItem(ItemStack item) {
        this(item, null);
    }
}
