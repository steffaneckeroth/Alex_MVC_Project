package com.edencoding.controllers;

import com.edencoding.models.FinancialAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable {

    @FXML
    private VBox container;
    private FinancialAccount account;
    private Button setTextButton;
    private Button clearHistoryButton;
    private ListView<String> historyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextButton = new Button();
        setTextButton.setText("Write Text");
        ((VBox) container).getChildren().add(setTextButton);
        setTextButton.setOnMouseClicked(mouseEvent -> {
            ((VBox)container).getChildren().add(new Label("hello sde"));
        });
        clearHistoryButton = new Button();
        clearHistoryButton.setText("Clear history");
        ((VBox) container).getChildren().add(clearHistoryButton);
        clearHistoryButton.setOnMouseClicked(mouseEvent -> {
            this.account.deleteHistory();
        });
    }
    public void setModel(FinancialAccount account) {
        historyList = new ListView<String>();
        ((VBox) container).getChildren().add(historyList);
        this.account = account;
        historyList.setItems(this.account.getHistory());
    }


}
