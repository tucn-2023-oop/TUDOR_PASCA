module com.example.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.datatransfer;
    requires java.desktop;
    requires org.apache.commons.lang3;

    opens com.example.passwordmanager to javafx.fxml;
    exports com.example.passwordmanager;
}