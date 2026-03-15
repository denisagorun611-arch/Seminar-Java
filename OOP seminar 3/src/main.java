public static void main(String[] args) {
    BankAccount account = new BankAccount("Ana Ionescu", 500.0);
    System.out.println("Account created: " + account);

    account.deposit(200.0);
    account.withdraw(100.0);


    try {
        account.withdraw(1000.0);
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }

    // Prevent invalid deposit
    try {
        account.deposit(-50.0);
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }

    System.out.println("Final state: " + account);
}
