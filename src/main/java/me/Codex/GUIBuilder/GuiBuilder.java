package me.Codex.GUIBuilder;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GuiBuilder extends GuiBaseBuilder<GuiBuilder> {

    @Override
    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, size, title);
        if (fillMaterial != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, new ItemBuilder(fillMaterial).SetName(Component.text(" ")).asItem());
                }
            }
        }

        items.forEach((slot, guiItem) -> inventory.setItem(slot, guiItem.item()));
        GuiListener.register(inventory, this);

        player.openInventory(inventory);
    }
}
