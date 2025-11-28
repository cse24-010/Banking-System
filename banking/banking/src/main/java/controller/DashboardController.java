package main.java.controller;

import main.java.gui.DashboardView;
import main.java.gui.AccountDialog;
import main.java.gui.TransactionDialog;
import main.java.service.BankService;
import main.java.model.Customer;
import main.java.model.Account;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.util.List;

public class DashboardController {
    private DashboardView dashboardView;
    private BankService bankService;
    private Customer currentCustomer;
    private Stage primaryStage;

    public DashboardController(Stage primaryStage, BankService bankService, Customer customer) {
        this.primaryStage = primaryStage;
        this.bankService = bankService;
        this.currentCustomer = customer;
        this.dashboardView = new DashboardView(primaryStage);
        setupEventHandlers();
        loadCustomerData();
    }

    private void setupEventHandlers() {
        dashboardView.getDepositButton().setOnAction(e -> handleDeposit());
        dashboardView.getWithdrawButton().setOnAction(e -> handleWithdraw());
        dashboardView.getOpenAccountButton().setOnAction(e -> handleOpenAccount());
        dashboardView.getLogoutButton().setOnAction(e -> handleLogout());

        dashboardView.getAccountsListView().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> handleAccountSelection(newValue));
    }

    private void loadCustomerData() {
        dashboardView.getWelcomeLabel().setText("Welcome, " + currentCustomer.getFirstName() + " " + currentCustomer.getSurname());
        refreshAccountsList();
    }

    private void refreshAccountsList() {
        List<Account> accounts = bankService.getCustomerAccounts(currentCustomer.getCustomerId());
        dashboardView.getAccountsListView().getItems().clear();

        for (Account account : accounts) {
            String accountInfo = String.format("%s - %s (BWP %.2f)",
                    account.getAccountNumber(),
                    account.getAccountType(),
                    account.getBalance());
            dashboardView.getAccountsListView().getItems().add(accountInfo);
        }
    }

    private void handleAccountSelection(String selectedAccount) {
        if (selectedAccount != null) {
            String accountNumber = selectedAccount.split(" - ")[0];
            Account account = bankService.findAccount(accountNumber);

            if (account != null) {
                String details = String.format(
                        "Account Number: %s\nType: %s\nBalance: BWP %.2f\nCustomer: %s %s",
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getBalance(),
                        account.getCustomer().getFirstName(),
                        account.getCustomer().getSurname()
                );
                dashboardView.getDetailsTextArea().setText(details);
            }
        }
    }

    private void handleDeposit() {
        TransactionDialog depositDialog = new TransactionDialog(primaryStage, "Deposit Funds");

        depositDialog.getSubmitButton().setOnAction(e -> {
            String accountNumber = depositDialog.getAccountNumber();
            double amount = depositDialog.getAmount();

            if (accountNumber.isEmpty() || amount <= 0) {
                showAlert("Error", "Please enter valid account number and amount.");
                return;
            }

            boolean success = bankService.deposit(accountNumber, amount);
            if (success) {
                showAlert("Success", "Deposit completed successfully!");
                refreshAccountsList();
                depositDialog.close();
            } else {
                showAlert("Error", "Deposit failed. Please check account number.");
            }
        });

        depositDialog.getCancelButton().setOnAction(e -> depositDialog.close());
        depositDialog.showAndWait();
    }

    private void handleWithdraw() {
        TransactionDialog withdrawDialog = new TransactionDialog(primaryStage, "Withdraw Funds");

        withdrawDialog.getSubmitButton().setOnAction(e -> {
            String accountNumber = withdrawDialog.getAccountNumber();
            double amount = withdrawDialog.getAmount();

            if (accountNumber.isEmpty() || amount <= 0) {
                showAlert("Error", "Please enter valid account number and amount.");
                return;
            }

            boolean success = bankService.withdraw(accountNumber, amount);
            if (success) {
                showAlert("Success", "Withdrawal completed successfully!");
                refreshAccountsList();
                withdrawDialog.close();
            } else {
                showAlert("Error", "Withdrawal failed. Check account number, type, or balance.");
            }
        });

        withdrawDialog.getCancelButton().setOnAction(e -> withdrawDialog.close());
        withdrawDialog.showAndWait();
    }

    private void handleOpenAccount() {
        AccountDialog accountDialog = new AccountDialog(primaryStage);

        accountDialog.getSubmitButton().setOnAction(e -> {
            String accountType = accountDialog.getAccountType();
            double initialDeposit = accountDialog.getInitialDeposit();
            String employer = accountDialog.getEmployer();
            String companyAddress = accountDialog.getCompanyAddress();

            String accountNumber = generateAccountNumber(accountType);
            boolean success = false;

            switch (accountType) {
                case "Savings":
                    bankService.openSavingsAccount(accountNumber, currentCustomer, "Main Branch");
                    success = true;
                    break;
                case "Investment":
                    if (initialDeposit >= 500) {
                        bankService.openInvestmentAccount(accountNumber, currentCustomer, "Main Branch", initialDeposit);
                        success = true;
                    } else {
                        showAlert("Error", "Investment account requires minimum BWP 500.00");
                    }
                    break;
                case "Cheque":
                    if (!employer.isEmpty() && !companyAddress.isEmpty()) {
                        bankService.openChequeAccount(accountNumber, currentCustomer, "Main Branch", employer, companyAddress);
                        success = true;
                    } else {
                        showAlert("Error", "Please provide employer and company address for cheque account.");
                    }
                    break;
            }

            if (success) {
                showAlert("Success", accountType + " account opened successfully!\nAccount Number: " + accountNumber);
                if (initialDeposit > 0 && !accountType.equals("Investment")) {
                    bankService.deposit(accountNumber, initialDeposit);
                }
                refreshAccountsList();
                accountDialog.close();
            }
        });

        accountDialog.getCancelButton().setOnAction(e -> accountDialog.close());
        accountDialog.showAndWait();
    }

    private void handleLogout() {
        LoginController loginController = new LoginController(primaryStage, bankService);
        loginController.showLogin();
    }

    private String generateAccountNumber(String accountType) {
        String prefix = "";
        switch (accountType) {
            case "Savings": prefix = "SAV"; break;
            case "Investment": prefix = "INV"; break;
            case "Cheque": prefix = "CHQ"; break;
        }
        return prefix + System.currentTimeMillis();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showDashboard() {
        dashboardView.show();
    }
}
