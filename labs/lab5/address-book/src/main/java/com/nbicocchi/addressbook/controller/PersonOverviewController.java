package com.nbicocchi.addressbook.controller;

import com.nbicocchi.addressbook.model.Person;
import com.nbicocchi.addressbook.client.PersonClient;
import com.nbicocchi.addressbook.client.RestClientException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;

public class PersonOverviewController {
    @FXML private SplitPane contentPane;
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
    @FXML private Button editButton;
    @FXML private Button deleteButton;

    private final ObservableList<Person> people = FXCollections.observableArrayList();
    private PersonClient client;
    private Executor executor;
    private boolean busy;

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
        showPersonDetails(null);
        contentPane.setDisable(true);
        statusLabel.setText("Waiting for backend configuration…");
    }

    public void configure(PersonClient client, Executor executor) {
        this.client = client;
        this.executor = executor;
        handleReload();
    }

    private void showPersonDetails(Person person) {
        boolean empty = person == null;
        firstNameLabel.setText(empty ? "" : person.getFirstName());
        lastNameLabel.setText(empty ? "" : person.getLastName());
        streetLabel.setText(empty ? "" : person.getStreet());
        postalCodeLabel.setText(empty ? "" : person.getPostalCode());
        cityLabel.setText(empty ? "" : person.getCity());
        birthdayLabel.setText(empty ? "" : person.getBirthday().toString());
        selectionLabel.setText(empty ? "Select a contact to see their details." : "Contact #" + person.getId());
        clearSelectionButton.setDisable(empty);
        editButton.setDisable(empty);
        deleteButton.setDisable(empty);
    }

    @FXML
    private void handleClearSelection() {
        personTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleReload() {
        runRemoteAction("Loading contacts…", () -> { });
    }

    @FXML
    private void handleNew() {
        editPerson(null).ifPresent(person ->
                runRemoteAction("Saving contact…", () -> client.create(person)));
    }

    @FXML
    private void handleEdit() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            editPerson(selected).ifPresent(person ->
                    runRemoteAction("Saving changes…", () -> client.update(person)));
        }
    }

    @FXML
    private void handleDelete() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete " + selected.getFirstName() + " " + selected.getLastName() + "?",
                ButtonType.OK, ButtonType.CANCEL);
        confirmation.initOwner(personTable.getScene().getWindow());
        confirmation.setHeaderText("Delete contact");
        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            runRemoteAction("Deleting contact…", () -> client.deleteById(selected.getId()));
        }
    }

    private Optional<Person> editPerson(Person original) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/nbicocchi/addressbook/person-edit-view.fxml"));
            DialogPane pane = loader.load();
            PersonEditDialogController editor = loader.getController();
            editor.setPerson(original);
            Dialog<Person> dialog = new Dialog<>();
            dialog.initOwner(personTable.getScene().getWindow());
            dialog.setTitle(original == null ? "New contact" : "Edit contact");
            dialog.setDialogPane(pane);
            pane.lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
                try {
                    editor.readPerson();
                } catch (IllegalArgumentException e) {
                    editor.showValidationError(e.getMessage());
                    event.consume();
                }
            });
            dialog.setResultConverter(button -> button == ButtonType.OK ? editor.readPerson() : null);
            return dialog.showAndWait();
        } catch (IOException e) {
            showError("Could not open the contact form.", e);
            return Optional.empty();
        }
    }

    private void runRemoteAction(String message, Runnable mutation) {
        if (busy) {
            return;
        }
        busy = true;
        contentPane.setDisable(true);
        statusLabel.setText(message);
        Task<List<Person>> task = new Task<>() {
            @Override
            protected List<Person> call() {
                mutation.run();
                return client.findAll();
            }
        };
        task.setOnSucceeded(event -> {
            personTable.getSelectionModel().clearSelection();
            people.setAll(task.getValue());
            personTable.sort();
            showPersonDetails(null);
            finishAction(people.size() + " contacts · REST backend · Lab 5");
        });
        task.setOnFailed(event -> {
            finishAction("Backend operation failed. Check the server, then reload.");
            Throwable failure = task.getException();
            String detail = failure instanceof RestClientException ? failure.getMessage() : "Unexpected client error.";
            showError(detail + " A write may already have completed: reload before retrying it.", failure);
        });
        executor.execute(task);
    }

    private void finishAction(String message) {
        busy = false;
        contentPane.setDisable(false);
        statusLabel.setText(message);
    }

    private void showError(String message, Throwable cause) {
        cause.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.initOwner(personTable.getScene().getWindow());
        alert.setHeaderText("Address book operation failed");
        alert.show();
    }
}
