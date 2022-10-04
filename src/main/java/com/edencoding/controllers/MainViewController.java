package com.edencoding.controllers;

import com.edencoding.models.FinancialAccount;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    //Model
    FinancialAccount account;

    @FXML private VBox vBox;
    //View nodes
    @FXML private Label accountHolder;
    @FXML private Label accountNumber;
    @FXML private Label accountBalance;
    @FXML private TextField amountTextField;

    public void initialize(){
        //get model
        account = new FinancialAccount("Maxwell Planck", 6626, 1000d);

        //link Model with View
        accountHolder.textProperty().bind(account.accountHolderProperty());
        accountBalance.textProperty().bind(account.accountBalanceProperty().asString());
        accountNumber.textProperty().bind(account.accountNumberProperty().asString());

        //link Controller to View - ensure only numeric input (integers) in text field
        amountTextField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().matches("\\d+") || change.getText().equals("")) {
                return change;
            } else {
                change.setText("");
                change.setRange(
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        }));
    }

    @FXML private void handleDeposit(Event event) {
        account.deposit(getAmount());
        event.consume();
    }

    @FXML private void handleWithdrawal(Event event) {
        account.withdraw(getAmount());
        event.consume();
    }

    @FXML private void openHistory(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/historyView.fxml"));
        loader.setController(new HistoryViewController());
        Parent root = loader.load();

        HistoryViewController h = (HistoryViewController) loader.getController();
        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("MVC Example App");
        stage.getIcons().add(new Image(getClass().getResource("/img/EdenCodingIcon.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
        h.setModel(account);
    }


    private double getAmount(){
        if (amountTextField.getText().equals("")) return 0;

        return Double.parseDouble(amountTextField.getText());
    }

    @FXML
    private void doubleBalance(ActionEvent actionEvent) {
        account.doubleAccBalance(getAmount());

    }
}
