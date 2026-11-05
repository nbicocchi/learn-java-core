module com.nbicocchi.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    exports com.nbicocchi.addressbook;
    opens com.nbicocchi.addressbook.controller to javafx.fxml;
    opens com.nbicocchi.addressbook.client to com.fasterxml.jackson.databind;
}
