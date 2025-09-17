package banking;

public class SavingsAccount extends Account implements InterestBearing {
    private static final double INTEREST_RATE = 0.0005; // 0.05% monthly

    public SavingsAccount(String no, String branch, Customer owner, double initial) {
        super(no, branch, owner, initial);
    }

    @Override public void withdraw(double amount) {
        System.out.println("SavingsAccount: withdrawals are not allowed.");
    }

    @Override public void applyMonthlyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Savings interest " + interest + " -> balance " + balance);
    }

    @Override public void displayDetails() {
        System.out.println("SavingsAccount{" + getAccountNumber() + ", " + getBalance() + "}");
    }
}
