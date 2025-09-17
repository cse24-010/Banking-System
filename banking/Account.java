package banking;

public abstract class Account {
    private final String accountNumber;
    protected double balance;
    private final String branch;
    private final Customer owner;

    public Account(String accountNumber, String branch, Customer owner, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        this.balance = Math.max(0, initialDeposit);
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getBranch() { return branch; }
    public Customer getOwner() { return owner; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive.");
        balance += amount;
        System.out.println("Deposited " + amount + " -> balance " + balance);
    }

    public abstract void withdraw(double amount);
    public abstract void displayDetails();
}
