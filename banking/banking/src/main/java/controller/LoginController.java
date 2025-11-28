package main.java.controller;

import main.java.gui.LoginView;
import main.java.gui.DashboardView;
import main.java.service.BankService;
import main.java.model.Customer;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class LoginController {
    private LoginView loginView;
    private BankService bankService;
    private Stage primaryStage;

    public LoginController(Stage primaryStage, BankService bankService) {
        this.primaryStage = primaryStage;
        this.bankService = bankService;
        this.loginView = new LoginView(primaryStage);
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        loginView.getLoginButton().setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        String customerId = loginView.getCustomerId();

        if (customerId == null || customerId.trim().isEmpty()) {
            showAlert("Error", "Please enter a Customer ID");
            return;
        }

        Customer customer = bankService.findCustomer(customerId);
        if (customer != null) {
            DashboardController dashboardController = new DashboardController(primaryStage, bankService, customer);
            dashboardController.showDashboard();
        } else {
            showAlert("Error", "Customer ID not found. Please try again.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showLogin() {
        loginView.show();
    }
}
