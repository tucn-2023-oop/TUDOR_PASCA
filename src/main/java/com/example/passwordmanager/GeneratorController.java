package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class GeneratorController extends Controller {
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

    public TextField getResult() {
        return result;
    }

    public void setLengthSlider(Slider lengthSlider) {
        this.lengthSlider = lengthSlider;
    }

    public void setCapitalLettersRadio(RadioButton capitalLettersRadio) {
        this.capitalLettersRadio = capitalLettersRadio;
    }

    public void setSmallLettersRadio(RadioButton smallLettersRadio) {
        this.smallLettersRadio = smallLettersRadio;
    }

    public void setDigitsRadio(RadioButton digitsRadio) {
        this.digitsRadio = digitsRadio;
    }

    public void setSpecialCharactersRadio(RadioButton specialCharactersRadio) {
        this.specialCharactersRadio = specialCharactersRadio;
    }

    public void setResult(TextField result) {
        this.result = result;
    }

    @FXML
    public void copyPasswordToClipboard() {
        if (result.getText().isEmpty())
            return;
        StringSelection stringSelection = new StringSelection(result.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public String getAvailableCharacters() {
        final String smallLetters = "abcdefghijklmnopqrstuvxyz";
        final String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String digits = "0123456789";
        final String specialCharacters = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        String result = "";
        if (smallLettersRadio.isSelected())
            result += smallLetters;
        if (capitalLettersRadio.isSelected())
            result += capitalLetters;
        if (digitsRadio.isSelected())
            result += digits;
        if (specialCharactersRadio.isSelected())
            result += specialCharacters;
        return result;
    }

    public StringBuilder generatePassword() {
        final String availableCharacters = getAvailableCharacters();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < lengthSlider.getValue(); i++) {
            int index = (int)(Math.random() * availableCharacters.length());
            password.append(availableCharacters.charAt(index));
        }
        return password;
    }
    @FXML
    public void setPassword() {
        StringBuilder password = generatePassword();
        result.setText(password.toString());
    }
}
