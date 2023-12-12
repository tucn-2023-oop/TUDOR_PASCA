module com.example.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.datatransfer;
    requires java.desktop;

    opens com.example.passwordmanager to javafx.fxml;
    exports com.example.passwordmanager;
}