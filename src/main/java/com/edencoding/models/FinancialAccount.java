package com.edencoding.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FinancialAccount {

    private ObservableList<String> history;
    private final StringProperty accountHolder;
    private final IntegerProperty accountNumber;
    private final DoubleProperty accountBalance;

    public FinancialAccount(String accountHolder, Integer accountNumber, Double accountBalance) {
        this.accountHolder = new SimpleStringProperty(accountHolder);
        this.accountNumber = new SimpleIntegerProperty(accountNumber);
        this.accountBalance = new SimpleDoubleProperty(accountBalance);
        history = FXCollections.observableArrayList();
    }

    public String getAccountHolder() {
        return accountHolder.get();
    }

    public ObservableList<String> getHistory() {
        return history;
    }

    public void addToHistory(String o) {
        this.history.add(o);
    }

    public StringProperty accountHolderProperty() {
        return accountHolder;
    }

    public int getAccountNumber() {
        return accountNumber.get();
    }

    public IntegerProperty accountNumberProperty() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance.get();
    }

    public DoubleProperty accountBalanceProperty() {
        return accountBalance;
    }
    
    public void deposit(double amount){
        accountBalance.set(accountBalance.get() + amount);
        this.history.add("Deposited "+amount);
    }

    public void withdraw(double amount){
        accountBalance.set(accountBalance.get() - amount);
        this.history.add("Withdrew "+amount);
    }

    public void deleteHistory() {
        this.history.setAll();
    }

    public void doubleAccBalance(double amount) {
        accountBalance.set(accountBalance.get() *2);
        this.history.add(accountBalance + "Get rich lol");
    }
}
