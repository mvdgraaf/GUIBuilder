
# GUIBuilder

A modern, fluent API for creating interactive inventories in Spigot/Paper plugins. Build GUIs with clickable items, pagination, and autofilled slots with ease.

## Installation

Add the following to your `pom.xml`:

### 1. Add the Repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### 2. Add the Dependency
```xml
<dependency>
    <groupId>com.github.mvdgraaf</groupId>
    <artifactId>GUIBuilder</artifactId>
    <version>Tag</version>
</dependency>
```
*Replace `Tag` with the latest version/commit hash.*

---

## Full Tutorial

### 1. Creating Items with ItemBuilder
The `ItemBuilder` allows you to create `ItemStack`s or `GuiItem`s fluently. It supports both legacy `&` color codes and Adventure `Component`s.

```java
// Create a simple GuiItem with a click action
GuiItem clickMe = new ItemBuilder(Material.DIAMOND)
        .SetName("&b&lClick Me!")
        .setLore("&7This is a special item", "&eClick to get a message!")
        .asGuiItem(event -> {
            event.getWhoClicked().sendMessage("You clicked the diamond!");
        });

// Create a static item (no action)
GuiItem decoration = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
        .SetName(" ")
        .asGuiItem();
```

### 2. Building a Simple GUI
Use `GuiBuilder` to create a standard inventory GUI.

```java
new GuiBuilder()
        .title("My Awesome GUI") // Supports String or Component
        .size(27)               // Rows * 9
        .fill(Material.GRAY_STAINED_GLASS_PANE) // Automatically fill empty slots
        .setItem(13, clickMe)   // Place your GuiItem
        .open(player);          // Open it for a player
```

### 3. Creating Paged GUIs
`PagedGuiBuilder` handles pagination for you when you have a large list of items.

```java
List<GuiItem> items = new ArrayList<>();
for (int i = 0; i < 50; i++) {
    items.add(new ItemBuilder(Material.GOLD_INGOT).SetName("&6Item #" + i).asGuiItem());
}

GuiItem nextButton = new ItemBuilder(Material.ARROW).SetName("&aNext Page").asGuiItem();
GuiItem prevButton = new ItemBuilder(Material.ARROW).SetName("&cPrevious Page").asGuiItem();

new PagedGuiBuilder(
        "Large Collection", // Title
        6,                  // Rows
        nextButton,         // Item for Next Page
        prevButton,         // Item for Previous Page
        items               // The list of items to display
).open(player);
```

---

## Features

* **Fluent API**: Chain methods for a cleaner codebase.
* **Adventure Support**: Native support for Kyori Adventure Components.
* **Automatic Event Handling**: No manual listener registration required.
* **Pagination**: Easy-to-use paged inventories.
* **Smart Filling**: Quickly fill empty slots with background items.

---

## Why Use GUIBuilder?

* **Speed**: Reduce boilerplate code for menus.
* **Modern**: Built for modern Minecraft versions (1.21+).
* **Flexible**: Use legacy strings or modern Components.
* **Lightweight**: No heavy dependencies, just Paper API.