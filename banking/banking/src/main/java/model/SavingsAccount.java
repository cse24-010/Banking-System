package main.java.model;

public class SavingsAccount extends AbstractAccount {
    private static final double MONTHLY_INTEREST_RATE = 0.0005;

    public SavingsAccount(String accountNumber, Customer customer, String branch) {
        super(accountNumber, customer, branch);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("Withdrawals not allowed from Savings account");
        return false;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * MONTHLY_INTEREST_RATE;
        balance += interest;
        System.out.println("Interest of BWP " + interest + " added to savings account: " + accountNumber);
    }
}
