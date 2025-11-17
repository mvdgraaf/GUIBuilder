package me.Codex.GUIBuilder;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedGuiBuilder implements Gui {
    private final Component title;
    private final int rows;
    private final GuiItem next, prev;
    private final List<GuiItem> content;

    private final int pageSize;

    public PagedGuiBuilder(String title, int rows, GuiItem next, GuiItem prev, List<GuiItem> content) {
        this.title = Component.text(title);
        this.rows = rows;
        this.next = next;
        this.prev = prev;
        this.content = content;
        pageSize = rows * 9;
    }

    @Override
    public void open(Player player) {
        openPage(player, 0);
    }

    public void open(Player player, int page) {
        openPage(player, page);
    }

    public void openPage(Player player, int page) {
        int maxPage = Math.max(0, (content.size() - 1) / pageSize);
        if (page < 0) page = 0;
        if (page > maxPage) page = maxPage;

        Inventory inventory = Bukkit.createInventory(null, pageSize, title);

        Map<Integer, GuiItem> clickables = new HashMap<>();

        // Content vullen
        int start = page * pageSize;
        for (int i = 0; i < pageSize; i++) {
            int index = start + i;
            if (index >= content.size()) break;
            GuiItem guiItem = content.get(index);
            inventory.setItem(i, guiItem.item());
            if (guiItem.action() != null) {
                clickables.put(i, guiItem);
            }
        }

        // Navigatieknoppen plaatsen op de laatste rij
        int prevSlot = pageSize - 9;      // Eerste slot van laatste rij
        int nextSlot = pageSize - 1;      // Laatste slot

        if (prev != null && page > 0) {
            inventory.setItem(prevSlot, prev.item());
            int finalPage1 = page;
            clickables.put(prevSlot, new GuiItem(prev.item(), e -> {
                if (prev.action() != null) prev.action().accept(e);
                openPage((Player) e.getWhoClicked(), finalPage1 - 1);
            }));
        }

        if (next != null && page < maxPage) {
            inventory.setItem(nextSlot, next.item());
            int finalPage = page;
            clickables.put(nextSlot, new GuiItem(next.item(), e -> {
                if (next.action() != null) next.action().accept(e);
                openPage((Player) e.getWhoClicked(), finalPage + 1);
            }));
        }

        GuiListener.registerPaged(inventory, clickables);
        player.openInventory(inventory);
    }
}
