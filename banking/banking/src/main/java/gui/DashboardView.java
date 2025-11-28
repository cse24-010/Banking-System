package main.java.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {
    private Stage stage;
    private ListView<String> accountsListView;
    private TextArea detailsTextArea;
    private Button depositButton;
    private Button withdrawButton;
    private Button openAccountButton;
    private Button logoutButton;
    private Label welcomeLabel;

    public DashboardView(Stage primaryStage) {
        this.stage = primaryStage;
        createView();
    }

    private void createView() {
        stage.setTitle("Banking System - Dashboard");

        welcomeLabel = new Label("Welcome!");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        accountsListView = new ListView<>();
        accountsListView.setPrefHeight(200);

        detailsTextArea = new TextArea();
        detailsTextArea.setPrefHeight(150);
        detailsTextArea.setEditable(false);

        depositButton = new Button("Deposit");
        withdrawButton = new Button("Withdraw");
        openAccountButton = new Button("Open New Account");
        logoutButton = new Button("Logout");

        VBox leftPanel = new VBox(10);
        leftPanel.setPadding(new Insets(10));
        leftPanel.getChildren().addAll(
                new Label("Your Accounts:"),
                accountsListView,
                new Label("Account Details:"),
                detailsTextArea
        );

        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.getChildren().addAll(
                depositButton,
                withdrawButton,
                openAccountButton,
                logoutButton
        );

        HBox mainContent = new HBox(10);
        mainContent.getChildren().addAll(leftPanel, rightPanel);

        BorderPane root = new BorderPane();
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, javafx.geometry.Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(10));
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
    }

    public ListView<String> getAccountsListView() { return accountsListView; }
    public TextArea getDetailsTextArea() { return detailsTextArea; }
    public Button getDepositButton() { return depositButton; }
    public Button getWithdrawButton() { return withdrawButton; }
    public Button getOpenAccountButton() { return openAccountButton; }
    public Button getLogoutButton() { return logoutButton; }
    public Label getWelcomeLabel() { return welcomeLabel; }

    public void show() {
        stage.show();
    }
}
