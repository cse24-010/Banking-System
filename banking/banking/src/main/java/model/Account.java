package main.java.model;

public interface Account {
    String getAccountNumber();
    double getBalance();
    String getAccountType();
    Customer getCustomer();
    void deposit(double amount);
    boolean withdraw(double amount);
    void calculateInterest();
    String getAccountDetails();
}
