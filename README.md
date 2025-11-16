
---

# Minecraft GUI Library

**Modern, Adventure-ready, and fluent API for creating interactive inventories in Spigot/Paper plugins. Build GUIs with clickable items, hover-text, and autofilled slots in just a few lines of code.**

---

## Features

* **Fluent ItemBuilder API**

    * Set item names with colors (`&` codes or Adventure Components)
    * Add lore lines and hover-text
    * Add click actions using `GuiItem`

* **GuiBuilder**

    * Create GUIs with custom size and Component-based titles
    * Fill empty slots automatically with a chosen material
    * Place clickable items easily
    * Automatic listener registration (plugins only register themselves once)

* **Lightweight & Reusable**

    * Can be used across multiple plugins
    * Minimal boilerplate for creating interactive GUIs

---

## Installation

1. Add the library JAR to your plugin’s `libs` folder.
2. Make sure to include it in your build path if using Gradle/Maven.
3. Register your plugin with the library in `onEnable()`:

```java
@Override
public void onEnable() {
    GuiRegistry.register(this); // Automatically registers the listener
}
```

---

## Usage Example

```java
// Create a clickable GUI item
GuiItem buyItem = new ItemBuilder(Material.DIAMOND)
        .SetName("&aBuy Item")
        .setLore("&7Click to purchase")
        .asGuiItem(e -> ((Player) e.getWhoClicked()).sendMessage("Bought!"));

// Create and open the GUI
new GuiBuilder(27, "&bShop")
        .fill(Material.GRAY_STAINED_GLASS_PANE)
        .setItem(13, buyItem)
        .open(player);
```

---

## Advanced Usage

* **Hover-text using Adventure Components:**

```java
ItemBuilder specialItem = new ItemBuilder(Material.EMERALD)
        .SetName(Component.text("Special Item")
                           .hoverEvent(HoverEvent.showText(Component.text("Extra info!"))));
```

* **Mixing legacy strings and Components**:

```java
ItemBuilder mixedItem = new ItemBuilder(Material.STONE)
        .SetName("&6Legacy Name")        // Legacy &-codes
        .setLore(Component.text("Hover info")); // Adventure Component
```

---

## Why Use This Library?

* Create professional-looking GUIs quickly
* Fully supports modern Minecraft formatting with Adventure
* Prevent accidental clicks with filler items
* Reusable across multiple projects

---

**This README was absolutely (not) written by ChatGPT.**