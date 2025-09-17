package banking;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final double INTEREST_RATE = 0.05; // 5% monthly

    public InvestmentAccount(String no, String branch, Customer owner, double initial) {
        super(no, branch, owner, initial);
        if (initial < 500.0)
            throw new IllegalArgumentException("Investment requires initial >= 500 BWP.");
    }

    @Override public void withdraw(double amount) {
        if (amount <= 0 || amount > balance)
            throw new IllegalArgumentException("Invalid withdrawal.");
        balance -= amount;
        System.out.println("Withdrew " + amount + " -> balance " + balance);
    }

    @Override public void applyMonthlyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Investment interest " + interest + " -> balance " + balance);
    }

    @Override public void displayDetails() {
        System.out.println("InvestmentAccount{" + getAccountNumber() + ", " + getBalance() + "}");
    }
}
