public class CoffeeMaker {
    public static void main(String[] args) {
        try {
            makeCoffee(); // Try to make coffee
        } catch (Exception e) {
            // Handle the error if the coffee machine breaks
            System.out.println("Machine broke! Making instant coffee instead.");
        } finally {
            // Clean up the kitchen regardless of what happened
            System.out.println("Clean the kitchen.");
        }
    }

    public static void makeCoffee() throws Exception {
        // Simulating a problem with the coffee machine
        throw new Exception("Coffee machine malfunction!");
    }
}

// Analogy: Making coffee is like trying something risky. If the machine breaks (error occurs),
// you handle it (make instant coffee) and clean up the kitchen no matter what.
