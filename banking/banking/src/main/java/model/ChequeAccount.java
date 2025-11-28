package main.java.model;

public class ChequeAccount extends AbstractAccount {
    private String employer;
    private String companyAddress;

    public ChequeAccount(String accountNumber, Customer customer, String branch,
                         String employer, String companyAddress) {
        super(accountNumber, customer, branch);
        this.employer = employer;
        this.companyAddress = companyAddress;
        customer.setEmploymentInfo("Employer: " + employer + ", Address: " + companyAddress);
    }

    @Override
    public String getAccountType() {
        return "Cheque";
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: BWP " + amount + " from cheque account: " + accountNumber);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
            return false;
        }
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest paid on cheque account: " + accountNumber);
    }

    public String getEmployer() {
        return employer;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    @Override
    public String getAccountDetails() {
        return super.getAccountDetails() + String.format(", Employer: %s", employer);
    }
}
