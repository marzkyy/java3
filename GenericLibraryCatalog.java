import java.util.*;

public class GenericLibraryCatalog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenericCatalog<LibraryItem> catalog = new GenericCatalog<>();

        while (true) {
            System.out.println("\nLibrary Catalog Menu:");
            System.out.println("1. Add a Library Item");
            System.out.println("2. Remove a Library Item");
            System.out.println("3. View Catalog");
            System.out.println("4. Search Catalog");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add a Library Item
                    System.out.print("Enter Item ID: ");
                    String itemID = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    LibraryItem newItem = new LibraryItem(title, author, itemID);
                    catalog.addItem(newItem);
                    break;

                case 2: // Remove a Library Item
                    System.out.print("Enter Item ID to remove: ");
                    String removeID = scanner.nextLine();
                    catalog.removeItem(removeID);
                    break;

                case 3: // View Catalog
                    catalog.displayItems();
                    break;

                case 4: // Search Catalog
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    catalog.searchItems(keyword);
                    break;

                case 5: // Exit
                    System.out.println("Exiting Library Catalog. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
