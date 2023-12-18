package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewController extends Controller implements Initializable {
    @FXML
    private TableView<Account> table;
    @FXML
    private Label titleLabel;
    @FXML
    private Label urlLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    private static int userId;
    private Account selectedAccount;

    public void setUserId(int userId) {
        MainViewController.userId = userId;
    }

    public static int getUserId() {
        return userId;
    }

    private void showAccountDetails(Account account) {
        if (account != null) {
            selectedAccount = account;
            titleLabel.setText(account.getTitle());
            urlLabel.setText(account.getUrl());
            usernameLabel.setText(account.getUsername());
            passwordLabel.setText(account.getPassword());
        } else {
            selectedAccount = null;
            titleLabel.setText("");
            urlLabel.setText("");
            usernameLabel.setText("");
            passwordLabel.setText("");
        }
    }
    @FXML
    public void goToEntryEditor() {
        if (selectedAccount == null)
            return;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("entry-editor-view.fxml"));
            AnchorPane root = (AnchorPane) fxmlLoader.load();
            EntryEditorController controller = fxmlLoader.getController();
            controller.setAccount(selectedAccount);
            controller.setUserId(userId);
            HelloApplication.changeScene("entry-editor-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getColumns().remove(1);
        table.getColumns().remove(0);

        TableColumn<Account, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        table.getColumns().add(titleColumn);

        TableColumn<Account, String> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        table.getColumns().add(urlColumn);

        TableColumn<Account, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        table.getColumns().add(usernameColumn);

        TableColumn<Account, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        table.getColumns().add(passwordColumn);

        ArrayList<Account> accounts = Database.getAccounts(userId);
        for (Account account: accounts)
            table.getItems().add(account);

        showAccountDetails(null);
        table.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showAccountDetails(newValue))
        );
    }

    private void refreshTable() {
        table.getItems().clear();
        ArrayList<Account> accounts = Database.getAccounts(userId);
        for (Account account: accounts)
            table.getItems().add(account);
    }

    @FXML
    public void deleteAccount() {
        Database.deleteAccountByTitle(titleLabel.getText());
        refreshTable();
    }

    private void copyStringToClipboard(String string) {
        if (string.isEmpty())
            return;
        StringSelection stringSelection = new StringSelection(string);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @FXML
    public void copyPasswordToClipboard() {
        copyStringToClipboard(passwordLabel.getText());
    }

    @FXML
    public void copyUsernameToClipboard() {
        copyStringToClipboard(usernameLabel.getText());
    }

    @FXML
    public void copyUrlToClipboard() { copyStringToClipboard(urlLabel.getText()); }
}
