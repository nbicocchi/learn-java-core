module com.nbicocchi.javafx.temperature {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.nbicocchi.javafx.temperature;
    opens com.nbicocchi.javafx.temperature to javafx.fxml;
}
