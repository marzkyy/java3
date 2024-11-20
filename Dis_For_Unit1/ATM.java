public class ATM {
    public static void main(String[] args) {
        try {
            withdrawMoney(500); // Attempt to withdraw money
        } catch (Exception e) {
            // Handle the error if there are insufficient funds
            System.out.println("Insufficient funds. Please try a smaller amount.");
        } finally {
            // Eject the card regardless of success or failure
            System.out.println("Ejecting card...");
        }
    }

    public static void withdrawMoney(int amount) throws Exception {
        int accountBalance = 200; // Simulate an account balance
        if (amount > accountBalance) {
            // Throw an exception if the amount exceeds the balance
            throw new Exception("Not enough money in account.");
        }
        System.out.println("Withdrawal successful!");
    }
}

// Analogy: Withdrawing money is like taking a risk at the ATM. If you don't have enough balance (error occurs),
// the ATM notifies you (error handling), but it always ejects your card regardless of success or failure.
