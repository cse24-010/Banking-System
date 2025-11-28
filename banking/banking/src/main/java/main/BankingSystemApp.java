package main.java.main;

import main.java.controller.LoginController;
import main.java.service.BankService;
import main.java.model.Customer;
import javafx.application.Application;
import javafx.stage.Stage;

public class BankingSystemApp extends Application {
    private BankService bankService;

    @Override
    public void start(Stage primaryStage) {
        bankService = new BankService();
        initializeSampleData();

        LoginController loginController = new LoginController(primaryStage, bankService);
        loginController.showLogin();
    }

    private void initializeSampleData() {
        // Sample customers
        bankService.registerCustomer("CUST001", "John", "Doe", "123 Main St, Gaborone");
        bankService.registerCustomer("CUST002", "Jane", "Smith", "456 Broad St, Francistown");
        bankService.registerCustomer("CUST003", "Mike", "Johnson", "789 River Rd, Maun");

        // Sample accounts
        bankService.openSavingsAccount("SAV001", bankService.findCustomer("CUST001"), "Gaborone Main");
        bankService.openInvestmentAccount("INV001", bankService.findCustomer("CUST001"), "Gaborone Main", 500.00);
        bankService.openChequeAccount("CHQ001", bankService.findCustomer("CUST001"), "Gaborone Main",
                "Botswana Govt", "Government Enclave");

        bankService.openSavingsAccount("SAV002", bankService.findCustomer("CUST002"), "Francistown Branch");
        bankService.openInvestmentAccount("INV002", bankService.findCustomer("CUST002"), "Francistown Branch", 750.00);

        // Initial deposits
        bankService.deposit("SAV001", 1500.00);
        bankService.deposit("INV001", 300.00);
        bankService.deposit("CHQ001", 2500.00);
        bankService.deposit("SAV002", 2000.00);
        bankService.deposit("INV002", 500.00);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
