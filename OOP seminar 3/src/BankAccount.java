public class BankAccount {
        private String ownerName;
        private double balance;

        public BankAccount(String ownerName, double initialBalance) {
            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }
            this.ownerName = ownerName;
            this.balance = initialBalance;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            balance += amount;
            System.out.println("Deposited: " + amount + " | New balance: " + balance);
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient funds. Cannot overdraft.");
            }
            balance -= amount;
            System.out.println("Withdrew: " + amount + " | New balance: " + balance);
        }

        @Override
        public String toString() {
            return "BankAccount{owner='" + ownerName + "', balance=" + balance + "}";
        }

}
