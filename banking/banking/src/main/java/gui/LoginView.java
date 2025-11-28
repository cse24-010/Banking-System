package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private Stage stage;
    private TextField customerIdField;
    private Button loginButton;

    public LoginView(Stage primaryStage) {
        this.stage = primaryStage;
        createView();
    }

    private void createView() {
        stage.setTitle("Banking System - Login");

        Label titleLabel = new Label("Banking System Login");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label customerIdLabel = new Label("Customer ID:");
        customerIdField = new TextField();
        customerIdField.setPromptText("Enter your customer ID");

        loginButton = new Button("Login");
        loginButton.setStyle("-fx-font-size: 14px; -fx-padding: 8px 16px;");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(customerIdLabel, 0, 1);
        grid.add(customerIdField, 1, 1);
        grid.add(loginButton, 1, 2);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(titleLabel, grid);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
    }

    public String getCustomerId() {
        return customerIdField.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getCustomerIdField() {
        return customerIdField;
    }

    public void show() {
        stage.show();
    }
}
