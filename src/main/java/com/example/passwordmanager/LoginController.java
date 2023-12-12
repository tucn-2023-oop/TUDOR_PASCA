package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLogin;

    @FXML
    public void userLogin() throws IOException {
        if (Database.isExistingUser(username.getText(), password.getText())) {
            int userId = Database.getUserId(username.getText(), password.getText());
            goToMainView(userId);
        } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your credentials!");
        } else {
            wrongLogin.setText("Wrong username or password!");
        }
    }
}
