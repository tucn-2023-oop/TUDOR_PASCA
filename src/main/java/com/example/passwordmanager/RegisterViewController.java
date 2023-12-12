package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterViewController extends Controller {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private Label usernameError;
    @FXML
    private Label emailError;

    private boolean isEmailValid(String email) {
        final String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    @FXML
    public void tryRegistration() throws IOException {
        usernameError.setText("");
        emailError.setText("");
        boolean invalidData = false;
        if (username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                email.getText().isEmpty()) {
            usernameError.setText("Incomplete data!");
            invalidData = true;
        }
        if (Database.isUserPropertyTaken("username", username.getText())) {
            usernameError.setText("Username is taken!");
            invalidData = true;
        }
        if (!isEmailValid(email.getText())) {
            emailError.setText("Email address is invalid!");
            invalidData = true;
        }
        if (Database.isUserPropertyTaken("email", email.getText())) {
            emailError.setText("Email address is taken!");
            invalidData = true;
        }
        if (invalidData)
            return;
        createAccount();
        int userId = Database.getUserId(username.getText(), password.getText());
        goToMainView(userId);
    }

    private void createAccount() {
        Database.writeUser(username.getText(), email.getText(), password.getText());
    }
}
