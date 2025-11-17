package me.Codex.GUIBuilder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    /**
     * Create a new ItemBuilder instance.
     *
     * @param material The material of the item.
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Set the display name of the item.
     *
     * @param name The new name of the item. (may contain &-codes).
     * @return The ItemBuilder instance for chaining.
     */
    public ItemBuilder SetName(String name) {
        Component displayName = SERIALIZER.deserialize(name);
        meta.displayName(displayName);
        return this;
    }


    /**
     * Set the display name of the item using a Component.
     *
     * @param name The Component to use as a display name.
     * @return The ItemBuilder instance for chaining.
     */
    public ItemBuilder SetName(Component name) {
        meta.displayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lines) {
        List<Component> lore = Arrays.stream(lines)
                .map(SERIALIZER::deserialize)
                .collect(Collectors.toList());
        meta.lore(lore);
        return this;
    }

    private ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    public GuiItem asGuiItem(Consumer<InventoryClickEvent> action) {
        build();
        return new GuiItem(item, action);
    }

    public GuiItem asGuiItem() {
        return new GuiItem(build());
    }

    public ItemStack asItem() {
        return build();
    }
}
