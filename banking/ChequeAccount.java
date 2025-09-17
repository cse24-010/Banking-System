package banking;

public class ChequeAccount extends Account {
    private final String companyName;
    private final String companyAddress;

    public ChequeAccount(String no, String branch, Customer owner, double initial,
                         String companyName, String companyAddress) {
        super(no, branch, owner, initial);
        if (companyName == null || companyName.isBlank() ||
            companyAddress == null || companyAddress.isBlank())
            throw new IllegalArgumentException("Employment details required.");
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    @Override public void withdraw(double amount) {
        if (amount <= 0 || amount > balance)
            throw new IllegalArgumentException("Invalid withdrawal.");
        balance -= amount;
        System.out.println("Withdrew " + amount + " -> balance " + balance);
    }

    @Override public void displayDetails() {
        System.out.println("ChequeAccount{" + getAccountNumber() + ", " + getBalance()
                + ", employer=" + companyName + "}");
    }
}
