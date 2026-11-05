package com.nbicocchi.addressbook.controller;

import com.nbicocchi.addressbook.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonEditDialogController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField streetField;
    @FXML private TextField postalCodeField;
    @FXML private TextField cityField;
    @FXML private DatePicker birthdayPicker;
    @FXML private Label errorLabel;
    private Long id;

    public void setPerson(Person person) {
        id = person == null ? null : person.getId();
        firstNameField.setText(person == null ? "" : person.getFirstName());
        lastNameField.setText(person == null ? "" : person.getLastName());
        streetField.setText(person == null ? "" : person.getStreet());
        postalCodeField.setText(person == null ? "" : person.getPostalCode());
        cityField.setText(person == null ? "" : person.getCity());
        birthdayPicker.setValue(person == null ? null : person.getBirthday());
        errorLabel.setText("");
    }

    public Person readPerson() {
        if (birthdayPicker.getValue() == null) {
            throw new IllegalArgumentException("Birthday is required. Choose a date from the calendar.");
        }
        return new Person(id, firstNameField.getText().strip(), lastNameField.getText().strip(),
                streetField.getText().strip(), postalCodeField.getText().strip(),
                cityField.getText().strip(), birthdayPicker.getValue());
    }

    public void showValidationError(String message) {
        errorLabel.setText(message);
    }
}
