public class BankAccount {
    private String ownerName;
    protected double balance;

    public BankAccount(String ownerName, double initialBalance) throws InvalidAmountException {
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public String getOwnerName() { return ownerName; }
    public double getBalance()   { return balance; }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive. Got: " + amount);
        }
        balance += amount;
        System.out.println("[" + ownerName + "] Deposited: " + amount + " | Balance: " + balance);
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive. Got: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Tried to withdraw " + amount + " but balance is " + balance);
        }
        balance -= amount;
        System.out.println("[" + ownerName + "] Withdrew: " + amount + " | Balance: " + balance);
    }

    @Override
    public String toString() {
        return "BankAccount{owner='" + ownerName + "', balance=" + balance + "}";
    }
}