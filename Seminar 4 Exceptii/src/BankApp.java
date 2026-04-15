public class BankApp {
    public static void main(String[] args) {

        System.out.println("=== BankAccount Demo ===");
        try {
            BankAccount acc = new BankAccount("Ion Popescu", 300.0);
            System.out.println("Created: " + acc);

            acc.deposit(150.0);
            acc.withdraw(100.0);

            // Trigger InsufficientFundsException
            acc.withdraw(1000.0);

        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
        }

        System.out.println("\n=== SavingsAccount Demo ===");
        try {
            SavingsAccount savings = new SavingsAccount("Maria Dumitrescu", 500.0, 0.05);
            System.out.println("Created: " + savings);

            savings.deposit(200.0);
            savings.applyInterest();
            savings.withdraw(100.0);

            // Trigger minimum balance rule
            savings.withdraw(700.0);

        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Insufficient funds: " + e.getMessage());
        }

        System.out.println("\n=== Invalid Init Demo ===");
        try {
            BankAccount bad = new BankAccount("Test", -100.0);
        } catch (InvalidAmountException e) {
            System.out.println("Invalid amount: " + e.getMessage());
        }
    }
}