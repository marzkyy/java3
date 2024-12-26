import java.util.*;

// Generic Catalog class
class GenericCatalog<T extends LibraryItem> {
    private Map<String, T> catalog;

    public GenericCatalog() {
        catalog = new HashMap<>();
    }

    // Add a new library item
    public void addItem(T item) {
        if (catalog.containsKey(item.getItemID())) {
            System.out.println("Item with ID " + item.getItemID() + " already exists.");
        } else {
            catalog.put(item.getItemID(), item);
            System.out.println("Item added successfully: " + item);
        }
    }

    // Remove a library item
    public void removeItem(String itemID) {
        if (catalog.containsKey(itemID)) {
            T removedItem = catalog.remove(itemID);
            System.out.println("Item removed successfully: " + removedItem);
        } else {
            System.out.println("Error: Item with ID " + itemID + " does not exist.");
        }
    }

    // Retrieve item details
    public T getItem(String itemID) {
        return catalog.get(itemID);
    }

    // Display all items
    public void displayItems() {
        if (catalog.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            System.out.println("Catalog Items:");
            catalog.values().forEach(System.out::println);
        }
    }

    // Search for items by title or author
    public void searchItems(String keyword) {
        boolean found = false;
        for (T item : catalog.values()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase()) || 
                item.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found matching the keyword: " + keyword);
        }
    }
}