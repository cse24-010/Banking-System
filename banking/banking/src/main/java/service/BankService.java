package main.java.service;

import main.java.model.Customer;
import main.java.model.Account;
import main.java.model.SavingsAccount;
import main.java.model.InvestmentAccount;
import main.java.model.ChequeAccount;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private List<Customer> customers;
    private List<Account> accounts;

    public BankService() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public Customer registerCustomer(String customerId, String firstName, String surname, String address) {
        Customer customer = new Customer(customerId, firstName, surname, address);
        customers.add(customer);
        System.out.println("Customer registered: " + customer.getFirstName() + " " + customer.getSurname());
        return customer;
    }

    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Account openSavingsAccount(String accountNumber, Customer customer, String branch) {
        SavingsAccount account = new SavingsAccount(accountNumber, customer, branch);
        accounts.add(account);
        System.out.println("Savings account opened: " + accountNumber);
        return account;
    }

    public Account openInvestmentAccount(String accountNumber, Customer customer, String branch, double initialDeposit) {
        try {
            InvestmentAccount account = new InvestmentAccount(accountNumber, customer, branch, initialDeposit);
            accounts.add(account);
            System.out.println("Investment account opened: " + accountNumber);
            return account;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to open investment account: " + e.getMessage());
            return null;
        }
    }

    public Account openChequeAccount(String accountNumber, Customer customer, String branch,
                                     String employer, String companyAddress) {
        ChequeAccount account = new ChequeAccount(accountNumber, customer, branch, employer, companyAddress);
        accounts.add(account);
        System.out.println("Cheque account opened: " + accountNumber);
        return account;
    }

    public List<Account> getCustomerAccounts(String customerId) {
        List<Account> customerAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getCustomer().getCustomerId().equals(customerId)) {
                customerAccounts.add(account);
            }
        }
        return customerAccounts;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    public void calculateMonthlyInterest() {
        System.out.println("\n=== Calculating Monthly Interest ===");
        for (Account account : accounts) {
            account.calculateInterest();
        }
    }

    public void displayAllAccounts() {
        System.out.println("\n=== All Bank Accounts ===");
        for (Account account : accounts) {
            System.out.println(account.getAccountDetails());
        }
    }

    public void displayCustomerAccounts(String customerId) {
        Customer customer = findCustomer(customerId);
        if (customer != null) {
            System.out.println("\n=== Accounts for " + customer.getFirstName() + " " + customer.getSurname() + " ===");
            List<Account> customerAccounts = getCustomerAccounts(customerId);
            for (Account account : customerAccounts) {
                System.out.println(account.getAccountDetails());
            }
        } else {
            System.out.println("Customer not found: " + customerId);
        }
    }
}
