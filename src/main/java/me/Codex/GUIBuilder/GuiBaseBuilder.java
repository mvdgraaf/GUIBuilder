package me.Codex.GUIBuilder;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.Map;

public abstract class GuiBaseBuilder<T extends GuiBaseBuilder<T>> implements Gui {

    protected Component title;
    protected InventoryType type = InventoryType.CHEST;
    protected int size = 9;
    protected Material fillMaterial = null;

    public static Map<Integer, GuiItem> items = new HashMap<>();

    public T title(String title) {
        this.title = Component.text(title);
        return (T) this;
    }

    public T title(Component title){
        this.title = title;
        return (T) this;
    }

    public T size(int size) {
        this.size = size;
        return (T) this;
    }

    public T type(InventoryType type) {
        this.type = type;
        if (type != InventoryType.CHEST) {
            this.size = type.getDefaultSize();
        }
        return (T) this;
    }

    public T fill(Material material) {
        this.fillMaterial = material;
        return (T) this;
    }

    public T setItem(int slot, GuiItem item) {
        items.put(slot, item);
        return (T) this;
    }

    protected int size() {
        return size;
    }

    @Override
    public void open(Player player) {

    }
}
