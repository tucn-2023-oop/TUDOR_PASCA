package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class Controller {
    @FXML
    void goToLogin() throws IOException {
        HelloApplication.changeScene("hello-view.fxml");
    }

    @FXML
    void goToRegister() throws IOException {
        HelloApplication.changeScene("register-view.fxml");
    }

    @FXML
    void goToMainView(int userId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MainViewController controller = fxmlLoader.getController();
        controller.setUserId(userId);
        HelloApplication.changeScene("main-view.fxml");
    }
}
