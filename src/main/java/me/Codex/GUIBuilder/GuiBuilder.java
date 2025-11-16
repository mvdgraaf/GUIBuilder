package me.Codex.GUIBuilder;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GuiBuilder {

    private Inventory inventory;
    private InventoryType type = InventoryType.CHEST;
    private Material fillMaterial = null;
    private int size = 27;
    private String title = "Menu";

    public GuiBuilder title(String title) {
        this.title = title;
        return this;
    }

    public GuiBuilder size(int size) {
        this.size = size;
        this.type = InventoryType.CHEST;
        return this;
    }

    public GuiBuilder type(InventoryType type) {
        this.type = type;

        if (type == InventoryType.CHEST) {
            this.size = type.getDefaultSize();
        }
        return this;
    }

    private void build() {
        Component titleComponent = Component.text(title);
        if (type == InventoryType.CHEST) {
            inventory = Bukkit.createInventory(null, size, titleComponent);
        } else {
            inventory = Bukkit.createInventory(null, type, titleComponent);
        }
    }

    public GuiBuilder setItem(int slot, GuiItem item) {
        inventory.setItem(slot, item.item());
        GuiListener.register(inventory, slot, item);
        return this;
    }

    public GuiBuilder fill(Material material) {
        this.fillMaterial = material;
        return this;
    }

    public void open(Player player) {

        if (fillMaterial != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, new ItemBuilder(fillMaterial).SetName(" ").asItem());
                }
            }
        }

        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
