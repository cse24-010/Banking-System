package main.java.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AccountDialog {
    private Stage dialog;
    private ComboBox<String> accountTypeCombo;
    private TextField initialDepositField;
    private TextField employerField;
    private TextField companyAddressField;
    private Button submitButton;
    private Button cancelButton;

    public AccountDialog(Stage owner) {
        dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Open New Account");

        createView();
    }

    private void createView() {
        Label typeLabel = new Label("Account Type:");
        accountTypeCombo = new ComboBox<>();
        accountTypeCombo.getItems().addAll("Savings", "Investment", "Cheque");
        accountTypeCombo.setValue("Savings");

        Label depositLabel = new Label("Initial Deposit:");
        initialDepositField = new TextField();
        initialDepositField.setText("0.00");

        Label employerLabel = new Label("Employer (Cheque only):");
        employerField = new TextField();
        employerField.setDisable(true);

        Label companyLabel = new Label("Company Address (Cheque only):");
        companyAddressField = new TextField();
        companyAddressField.setDisable(true);

        submitButton = new Button("Open Account");
        cancelButton = new Button("Cancel");

        accountTypeCombo.setOnAction(e -> {
            String selectedType = accountTypeCombo.getValue();
            boolean isCheque = "Cheque".equals(selectedType);
            employerField.setDisable(!isCheque);
            companyAddressField.setDisable(!isCheque);

            if ("Investment".equals(selectedType)) {
                initialDepositField.setText("500.00");
            } else {
                initialDepositField.setText("0.00");
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(typeLabel, 0, 0);
        grid.add(accountTypeCombo, 1, 0);
        grid.add(depositLabel, 0, 1);
        grid.add(initialDepositField, 1, 1);
        grid.add(employerLabel, 0, 2);
        grid.add(employerField, 1, 2);
        grid.add(companyLabel, 0, 3);
        grid.add(companyAddressField, 1, 3);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(submitButton, cancelButton);
        grid.add(buttonBox, 1, 4);

        Scene scene = new Scene(grid);
        dialog.setScene(scene);
    }

    public String getAccountType() { return accountTypeCombo.getValue(); }
    public double getInitialDeposit() {
        try {
            return Double.parseDouble(initialDepositField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public String getEmployer() { return employerField.getText(); }
    public String getCompanyAddress() { return companyAddressField.getText(); }
    public Button getSubmitButton() { return submitButton; }
    public Button getCancelButton() { return cancelButton; }

    public void showAndWait() { dialog.showAndWait(); }
    public void close() { dialog.close(); }
}
