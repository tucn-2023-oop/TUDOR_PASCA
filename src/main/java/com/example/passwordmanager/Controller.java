package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {
    @FXML
    void goToLogin() {
        try {
            HelloApplication.changeScene("hello-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @FXML
    void goToRegister() {
        try {
            HelloApplication.changeScene("register-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }



    @FXML
    void setUserId(int userId) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            MainViewController controller = fxmlLoader.getController();
            controller.setUserId(userId);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @FXML
    void goToMainView() {
        try {
            HelloApplication.changeScene("main-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @FXML
    void goToGeneratorView() {
        try {
            HelloApplication.changeScene("generator-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @FXML
    void goToEntryCreationView() {
        try {
            HelloApplication.changeScene("entry-creation-view.fxml");
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
