class LibraryCatalogTests {
    public static void runTests(GenericCatalog<LibraryItem> catalog) {
        System.out.println("Running tests...");

        // Test 1: Adding items to the catalog
        LibraryItem book = new LibraryItem("The Great Gatsby", "F. Scott Fitzgerald", "101");
        LibraryItem dvd = new LibraryItem("Inception", "Christopher Nolan", "102");
        catalog.addItem(book);
        catalog.addItem(dvd);

        // Test 2: Adding a duplicate item
        System.out.println("Adding duplicate item:");
        catalog.addItem(book);

        // Test 3: Removing an item
        System.out.println("Removing an existing item:");
        catalog.removeItem("101");

        // Test 4: Removing a non-existent item
        System.out.println("Removing a non-existent item:");
        catalog.removeItem("999");

        // Test 5: Displaying all items
        System.out.println("Displaying catalog items:");
        catalog.displayItems();

        System.out.println("All tests completed successfully.");
    }
}