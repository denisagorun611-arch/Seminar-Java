public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String ownerName, double initialBalance, double interestRate)
            throws InvalidAmountException {
        super(ownerName, initialBalance);
        if (interestRate < 0) {
            throw new InvalidAmountException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("[" + getOwnerName() + "] Interest applied: +" + interest +
                " | New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (balance - amount < 100) {
            throw new InsufficientFundsException(
                    "SavingsAccount must maintain a minimum balance of 100. Cannot withdraw " + amount);
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return "SavingsAccount{owner='" + getOwnerName() + "', balance=" + balance +
                ", interestRate=" + interestRate + "}";
    }
}