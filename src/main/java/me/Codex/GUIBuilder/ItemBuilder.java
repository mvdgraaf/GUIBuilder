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
     * @param material the material of the item.
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Set the display name of the item.
     *
     * @param name The new name of the item. (may contain &-codes).
     * @return the ItemBuilder instance for chaining.
     */
    @Deprecated
    public ItemBuilder SetName(String name) {
        Component displayName = SERIALIZER.deserialize(name);
        meta.displayName(displayName);
        return this;
    }


    /**
     * Set the display name of the item using a Component.
     *
     * @param name The Component to use as a display name.
     * @return the ItemBuilder instance for chaining.
     */
    public ItemBuilder SetName(Component name) {
        meta.displayName(name);
        return this;
    }

    /**
     * Set the lore of the item.
     *
     * @param lines the lines to use as lore. (may contain &-codes).
     * @return the ItemBuilder instance for chaining.
     */
    @Deprecated
    public ItemBuilder setLore(String... lines) {
        List<Component> lore = Arrays.stream(lines)
                .map(SERIALIZER::deserialize)
                .collect(Collectors.toList());
        meta.lore(lore);
        return this;
    }

    /**
     * Set the lore of the item.
     *
     * @param lines The Component(s) to use as a lore.
     * @return the ItemBuilder instance for chaining.
     */
    public ItemBuilder setLore(Component... lines) {
        meta.lore(Arrays.asList(lines));
        return this;
    }

    private ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Builds the item and converts it into a {@link GuiItem}.
     *
     * @param action the action that will be executed when the item is clicked
     * @return the built item wrapped as a {@link GuiItem} for use in a GUI
     */
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
