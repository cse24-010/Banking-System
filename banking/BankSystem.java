package banking;

import java.util.*;

public class BankSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Bank System ===");
        while (true) {
            System.out.println("""
                1) Create customer
                2) Open account
                3) Deposit
                4) Withdraw
                5) Apply monthly interest (all)
                6) List customers & accounts
                0) Exit
                """);
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1 -> createCustomer();
                    case 2 -> openAccount();
                    case 3 -> deposit();
                    case 4 -> withdraw();
                    case 5 -> applyInterestAll();
                    case 6 -> listAll();
                    case 0 -> { System.out.println("Bye"); return; }
                    default -> System.out.println("Invalid.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // --- helpers ---
    static Customer pickCustomer() {
        if (customers.isEmpty()) throw new IllegalStateException("No customers yet.");
        for (int i = 0; i < customers.size(); i++)
            System.out.println(i + ") " + customers.get(i).getName());
        int idx = readInt("Customer #: ");
        return customers.get(idx);
    }
    static Account pickAccount(Customer c) {
        List<Account> accs = c.getAccounts();
        if (accs.isEmpty()) throw new IllegalStateException("No accounts for that customer.");
        for (int i = 0; i < accs.size(); i++)
            System.out.println(i + ") " + accs.get(i).getClass().getSimpleName()
                    + " " + accs.get(i).getAccountNumber() + " bal=" + accs.get(i).getBalance());
        int idx = readInt("Account #: ");
        return accs.get(idx);
    }
    static int readInt(String msg) { System.out.print(msg); return Integer.parseInt(sc.nextLine()); }
    static double readDouble(String msg) { System.out.print(msg); return Double.parseDouble(sc.nextLine()); }

    // --- menu actions ---
    static void createCustomer() {
        System.out.print("First name: "); String fn = sc.nextLine();
        System.out.print("Last name: ");  String ln = sc.nextLine();
        System.out.print("Address: ");    String ad = sc.nextLine();
        customers.add(new Customer(fn, ln, ad));
        System.out.println("Customer created.");
    }

    static void openAccount() {
        Customer c = pickCustomer();
        System.out.println("Type: 1) Savings  2) Investment  3) Cheque");
        int t = readInt("Choice: ");
        System.out.print("Account number: "); String no = sc.nextLine();
        System.out.print("Branch: ");         String br = sc.nextLine();
        double initial = readDouble("Initial deposit: ");
        Account a;
        switch (t) {
            case 1 -> a = new SavingsAccount(no, br, c, initial);
            case 2 -> a = new InvestmentAccount(no, br, c, initial); // enforces >= 500
            case 3 -> {
                System.out.print("Company name: "); String cn = sc.nextLine();
                System.out.print("Company address: "); String ca = sc.nextLine();
                a = new ChequeAccount(no, br, c, initial, cn, ca);
            }
            default -> throw new IllegalArgumentException("Unknown type.");
        }
        c.addAccount(a);
        System.out.println("Account opened.");
    }

    static void deposit() {
        Customer c = pickCustomer();
        Account a = pickAccount(c);
        double amt = readDouble("Amount to deposit: ");
        a.deposit(amt);
    }

    static void withdraw() {
        Customer c = pickCustomer();
        Account a = pickAccount(c);
        double amt = readDouble("Amount to withdraw: ");
        a.withdraw(amt); // Savings will refuse; others enforce rules
    }

    static void applyInterestAll() {
        for (Customer c : customers)
            for (Account a : c.getAccounts())
                if (a instanceof InterestBearing ib) ib.applyMonthlyInterest();
        System.out.println("Interest applied to Savings & Investment accounts.");
    }

    static void listAll() {
        for (Customer c : customers) {
            System.out.println("== " + c.getName() + " ==");
            c.showAccounts();
        }
    }
}
