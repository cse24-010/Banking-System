package main.java.model;

public class InvestmentAccount extends AbstractAccount {
    private static final double MONTHLY_INTEREST_RATE = 0.05;
    private static final double MIN_OPENING_BALANCE = 500.00;

    public InvestmentAccount(String accountNumber, Customer customer, String branch, double initialDeposit) {
        super(accountNumber, customer, branch);
        if (initialDeposit >= MIN_OPENING_BALANCE) {
            this.balance = initialDeposit;
        } else {
            throw new IllegalArgumentException("Investment account requires minimum opening balance of BWP 500.00");
        }
    }

    @Override
    public String getAccountType() {
        return "Investment";
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: BWP " + amount + " from investment account: " + accountNumber);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
            return false;
        }
    }

    @Override
    public void calculateInterest() {
        double interest = balance * MONTHLY_INTEREST_RATE;
        balance += interest;
        System.out.println("Interest of BWP " + interest + " added to investment account: " + accountNumber);
    }
}
