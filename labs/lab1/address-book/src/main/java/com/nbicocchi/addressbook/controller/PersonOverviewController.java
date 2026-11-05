package com.nbicocchi.addressbook.controller;

import com.nbicocchi.addressbook.model.Person;
import com.nbicocchi.addressbook.model.SampleContacts;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
    @FXML private TableView<Person> personTable;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label selectionLabel;
    @FXML private Label statusLabel;
    @FXML private Button clearSelectionButton;

    private final ObservableList<Person> people = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cell ->
                new ReadOnlyStringWrapper(cell.getValue().getLastName()));
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        personTable.setItems(people);
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldPerson, newPerson) -> showPersonDetails(newPerson));
        handleReloadSamples();
    }

    private void showPersonDetails(Person person) {
        boolean empty = person == null;
        firstNameLabel.setText(empty ? "" : person.getFirstName());
        lastNameLabel.setText(empty ? "" : person.getLastName());
        streetLabel.setText(empty ? "" : person.getStreet());
        postalCodeLabel.setText(empty ? "" : person.getPostalCode());
        cityLabel.setText(empty ? "" : person.getCity());
        birthdayLabel.setText(empty ? "" : person.getBirthday().toString());
        selectionLabel.setText(empty ? "Select a contact to see their details." : "Selected contact");
        clearSelectionButton.setDisable(empty);
    }

    @FXML
    private void handleClearSelection() {
        personTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleReloadSamples() {
        personTable.getSelectionModel().clearSelection();
        personTable.getSortOrder().clear();
        people.setAll(SampleContacts.create());
        showPersonDetails(null);
        statusLabel.setText(people.size() + " contacts · In memory only · Lab 1");
    }
}
