// Testing class
class GenericLibraryCatalogTest {
    public static void main(String[] args) {
        GenericCatalog<LibraryItem> catalog = new GenericCatalog<>();

        // Add items
        catalog.addItem(new LibraryItem("The Great Gatsby", "F. Scott Fitzgerald", "001"));
        catalog.addItem(new LibraryItem("1984", "George Orwell", "002"));
        catalog.addItem(new LibraryItem("To Kill a Mockingbird", "Harper Lee", "003"));

        // Attempt to add duplicate
        catalog.addItem(new LibraryItem("Duplicate", "Author", "001"));

        // Display all items
        System.out.println("\nDisplaying all items:");
        catalog.displayItems();

        // Search for items
        System.out.println("\nSearch results for 'George':");
        catalog.searchItems("George");

        System.out.println("\nSearch results for 'Mockingbird':");
        catalog.searchItems("Mockingbird");

        // Remove an item
        System.out.println("\nRemoving item with ID '002':");
        catalog.removeItem("002");

        // Attempt to remove a non-existent item
        System.out.println("\nRemoving non-existent item with ID '999':");
        catalog.removeItem("999");

        // Display all items again
        System.out.println("\nDisplaying all items after removal:");
        catalog.displayItems();
    }
}