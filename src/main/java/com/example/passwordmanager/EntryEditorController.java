package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryEditorController extends Controller implements Initializable {
    private static Account account;
    private static int userId;
    @FXML
    private TextField titleField;
    @FXML
    private TextField urlField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label warningLabel;

    public void setAccount(Account account) {
        EntryEditorController.account = account;
    }

    public void setUserId(int userId) {
        EntryEditorController.userId = userId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (account == null)
            return;
        titleField.setText(account.getTitle());
        urlField.setText(account.getUrl());
        usernameField.setText(account.getUsername());
        passwordField.setText(account.getPassword());
    }

    @FXML
    public void saveAccountChanges() {
        String title = titleField.getText();
        String url = urlField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (title.isEmpty() || url.isEmpty() || username.isEmpty() || password.isEmpty()) {
            warningLabel.setText("All fields are mandatory!");
            return;
        }
        if (!title.equals(account.getTitle()) && Database.isTitleTakenByUserId(userId, title)) {
            warningLabel.setText("The title is already used by another entry!");
            return;
        }
        Account newAccount = new Account(title, url, username, password);
        Database.updateAccount(userId, account, newAccount);
        goToMainView();
    }
}
