package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryCreationController extends Controller implements Initializable {
    private GeneratorController generatorController;
    @FXML
    private Slider lengthSlider;
    @FXML
    private RadioButton capitalLettersRadio;
    @FXML
    private RadioButton smallLettersRadio;
    @FXML
    private RadioButton digitsRadio;
    @FXML
    private RadioButton specialCharactersRadio;
    @FXML
    private TextField result;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generatorController = new GeneratorController();
        generatorController.setDigitsRadio(digitsRadio);
        generatorController.setCapitalLettersRadio(capitalLettersRadio);
        generatorController.setSmallLettersRadio(smallLettersRadio);
        generatorController.setSpecialCharactersRadio(specialCharactersRadio);
        generatorController.setLengthSlider(lengthSlider);
        generatorController.setResult(result);
    }

    @FXML
    public void copyPasswordToClipboard() {
        generatorController.copyPasswordToClipboard();
    }

    @FXML
    public void setPasswordToTextField() {
        if (generatorController.getResult().getText().isEmpty())
            return;
        passwordField.setText(generatorController.getResult().getText());
    }

    @FXML
    public void generatePassword() {
        generatorController.setPassword();
    }

    @FXML
    public void writeAccountEntry() {
        if (titleField.getText().isEmpty() ||
                urlField.getText().isEmpty() ||
                usernameField.getText().isEmpty() ||
                passwordField.getText().isEmpty()) {
            warningLabel.setText("All fields are mandatory!");
            return;
        }
        int userId = MainViewController.getUserId();
        if (Database.isTitleTakenByUserId(userId, titleField.getText())) {
            warningLabel.setText("The title is already used by another entry!");
            return;
        }
        Database.writeAccount(userId, titleField.getText(), urlField.getText(), usernameField.getText(), passwordField.getText());
        goToMainView();
    }
}
