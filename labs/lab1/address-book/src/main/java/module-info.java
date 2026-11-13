module com.nbicocchi.addressbook {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.nbicocchi.addressbook;
    opens com.nbicocchi.addressbook.controller to javafx.fxml;
}
