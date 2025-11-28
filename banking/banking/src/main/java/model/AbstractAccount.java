package main.java.model;

public abstract class AbstractAccount implements Account {
    protected String accountNumber;
    protected double balance;
    protected Customer customer;
    protected String branch;

    public AbstractAccount(String accountNumber, Customer customer, String branch) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.branch = branch;
        this.balance = 0.0;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    public String getBranch() {
        return branch;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: BWP " + amount + " into account: " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    @Override
    public abstract boolean withdraw(double amount);

    @Override
    public abstract void calculateInterest();

    @Override
    public String getAccountDetails() {
        return String.format("Account: %s, Type: %s, Balance: BWP %.2f, Customer: %s %s",
                accountNumber, getAccountType(), balance, customer.getFirstName(), customer.getSurname());
    }
}
