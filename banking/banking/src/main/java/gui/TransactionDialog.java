package main.java.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TransactionDialog {
    private Stage dialog;
    private TextField amountField;
    private TextField accountNumberField;
    private Button submitButton;
    private Button cancelButton;

    public TransactionDialog(Stage owner, String title) {
        dialog = new Stage();
        dialog.initOwner(owner);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        createView();
    }

    private void createView() {
        Label accountLabel = new Label("Account Number:");
        accountNumberField = new TextField();

        Label amountLabel = new Label("Amount:");
        amountField = new TextField();

        submitButton = new Button("Submit");
        cancelButton = new Button("Cancel");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(accountLabel, 0, 0);
        grid.add(accountNumberField, 1, 0);
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(submitButton, cancelButton);
        grid.add(buttonBox, 1, 2);

        Scene scene = new Scene(grid);
        dialog.setScene(scene);
    }

    public String getAccountNumber() { return accountNumberField.getText(); }
    public double getAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    public Button getSubmitButton() { return submitButton; }
    public Button getCancelButton() { return cancelButton; }

    public void showAndWait() { dialog.showAndWait(); }
    public void close() { dialog.close(); }
}
