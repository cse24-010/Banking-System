package banking;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String firstName, lastName, address;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName; this.lastName = lastName; this.address = address;
    }

    public void addAccount(Account a) { accounts.add(a); }
    public List<Account> getAccounts() { return accounts; }
    public String getName() { return firstName + " " + lastName; }

    public void showAccounts() { accounts.forEach(Account::displayDetails); }
}
