import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create sample products
        Product product1 = new Product("P001", "Laptop", 1200.00);
        Product product2 = new Product("P002", "Smartphone", 800.00);
        Product product3 = new Product("P003", "Headphones", 150.00);

        // Display available products
        System.out.println("Available Products:");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        // Create a customer
        Customer customer = new Customer("C001", "John Doe");
        System.out.println("\nCustomer created: " + customer.getName());

        // Simulate browsing and adding products to the shopping cart
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the product IDs to add to the cart (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            switch (input) {
                case "P001":
                    customer.addToCart(product1);
                    break;
                case "P002":
                    customer.addToCart(product2);
                    break;
                case "P003":
                    customer.addToCart(product3);
                    break;
                default:
                    System.out.println("Invalid product ID.");
            }
        }

        // Display shopping cart
        System.out.println("\nShopping Cart:");
        customer.getShoppingCart().forEach(System.out::println);

        // Calculate total cost
        double totalCost = customer.calculateTotalCost();
        System.out.println("Total Cost: $" + totalCost + "\n");

        // Place an order
        Order order = new Order("O001", customer);

        // Display order summary
        System.out.println(order.getOrderSummary());

        // Update and display order status
        order.updateStatus("Shipped");
        System.out.println("Final Order Status: " + order.getStatus());

        scanner.close();
    }
}
