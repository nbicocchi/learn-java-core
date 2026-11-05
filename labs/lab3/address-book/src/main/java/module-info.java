module com.nbicocchi.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    exports com.nbicocchi.addressbook;
    opens com.nbicocchi.addressbook.controller to javafx.fxml;
}
